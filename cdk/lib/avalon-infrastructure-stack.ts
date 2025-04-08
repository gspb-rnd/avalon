import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as ec2 from 'aws-cdk-lib/aws-ec2';
import * as ecr from 'aws-cdk-lib/aws-ecr';
import * as ecs from 'aws-cdk-lib/aws-ecs';
import * as elbv2 from 'aws-cdk-lib/aws-elasticloadbalancingv2';
import * as rds from 'aws-cdk-lib/aws-rds';
import * as s3 from 'aws-cdk-lib/aws-s3';
import * as cloudfront from 'aws-cdk-lib/aws-cloudfront';
import * as origins from 'aws-cdk-lib/aws-cloudfront-origins';
import * as iam from 'aws-cdk-lib/aws-iam';
import * as logs from 'aws-cdk-lib/aws-logs';

export class AvalonInfrastructureStack extends cdk.Stack {
  public readonly vpc: ec2.Vpc;
  public readonly cluster: ecs.Cluster;
  public readonly database: rds.DatabaseInstance;
  public readonly frontendBucket: s3.Bucket;
  public readonly frontendDistribution: cloudfront.Distribution;
  public readonly loadBalancer: elbv2.ApplicationLoadBalancer;
  public readonly ecrRepositories: { [key: string]: ecr.Repository };

  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    this.vpc = new ec2.Vpc(this, 'AvalonVpc', {
      maxAzs: 3,
      natGateways: 1,
    });

    this.ecrRepositories = {
      serviceRegistry: new ecr.Repository(this, 'ServiceRegistryRepo', {
        repositoryName: 'avalon-service-registry',
        imageScanOnPush: true,
        removalPolicy: cdk.RemovalPolicy.RETAIN,
      }),
      apiGateway: new ecr.Repository(this, 'ApiGatewayRepo', {
        repositoryName: 'avalon-api-gateway',
        imageScanOnPush: true,
        removalPolicy: cdk.RemovalPolicy.RETAIN,
      }),
      clientService: new ecr.Repository(this, 'ClientServiceRepo', {
        repositoryName: 'avalon-client-service',
        imageScanOnPush: true,
        removalPolicy: cdk.RemovalPolicy.RETAIN,
      }),
      loanApplicationService: new ecr.Repository(this, 'LoanApplicationServiceRepo', {
        repositoryName: 'avalon-loan-application-service',
        imageScanOnPush: true,
        removalPolicy: cdk.RemovalPolicy.RETAIN,
      }),
      documentService: new ecr.Repository(this, 'DocumentServiceRepo', {
        repositoryName: 'avalon-document-service',
        imageScanOnPush: true,
        removalPolicy: cdk.RemovalPolicy.RETAIN,
      }),
      workflowService: new ecr.Repository(this, 'WorkflowServiceRepo', {
        repositoryName: 'avalon-workflow-service',
        imageScanOnPush: true,
        removalPolicy: cdk.RemovalPolicy.RETAIN,
      }),
    };

    this.cluster = new ecs.Cluster(this, 'AvalonCluster', {
      vpc: this.vpc,
      containerInsights: true,
    });

    const dbSecurityGroup = new ec2.SecurityGroup(this, 'DatabaseSecurityGroup', {
      vpc: this.vpc,
      description: 'Security group for Avalon RDS instance',
      allowAllOutbound: true,
    });

    dbSecurityGroup.addIngressRule(
      ec2.Peer.ipv4(this.vpc.vpcCidrBlock),
      ec2.Port.tcp(5432),
      'Allow database access from within VPC'
    );

    this.database = new rds.DatabaseInstance(this, 'AvalonDatabase', {
      engine: rds.DatabaseInstanceEngine.postgres({
        version: rds.PostgresEngineVersion.VER_13,
      }),
      instanceType: ec2.InstanceType.of(ec2.InstanceClass.BURSTABLE3, ec2.InstanceSize.SMALL),
      vpc: this.vpc,
      vpcSubnets: {
        subnetType: ec2.SubnetType.PRIVATE_WITH_EGRESS,
      },
      securityGroups: [dbSecurityGroup],
      allocatedStorage: 20,
      storageType: rds.StorageType.GP2,
      databaseName: 'avalon',
      credentials: rds.Credentials.fromGeneratedSecret('avalonadmin'),
      backupRetention: cdk.Duration.days(7),
      deleteAutomatedBackups: true,
      removalPolicy: cdk.RemovalPolicy.SNAPSHOT,
    });

    this.frontendBucket = new s3.Bucket(this, 'FrontendBucket', {
      bucketName: `avalon-frontend-${this.account}-${this.region}`,
      websiteIndexDocument: 'index.html',
      websiteErrorDocument: 'index.html',
      publicReadAccess: true,
      blockPublicAccess: new s3.BlockPublicAccess({
        blockPublicAcls: false,
        blockPublicPolicy: false,
        ignorePublicAcls: false,
        restrictPublicBuckets: false,
      }),
      removalPolicy: cdk.RemovalPolicy.DESTROY,
      autoDeleteObjects: true,
    });

    this.frontendDistribution = new cloudfront.Distribution(this, 'FrontendDistribution', {
      defaultBehavior: {
        origin: new origins.S3Origin(this.frontendBucket),
        viewerProtocolPolicy: cloudfront.ViewerProtocolPolicy.REDIRECT_TO_HTTPS,
        allowedMethods: cloudfront.AllowedMethods.ALLOW_GET_HEAD_OPTIONS,
        cachedMethods: cloudfront.CachedMethods.CACHE_GET_HEAD_OPTIONS,
        cachePolicy: cloudfront.CachePolicy.CACHING_OPTIMIZED,
      },
      errorResponses: [
        {
          httpStatus: 404,
          responseHttpStatus: 200,
          responsePagePath: '/index.html',
        },
      ],
      priceClass: cloudfront.PriceClass.PRICE_CLASS_100,
    });

    const albSecurityGroup = new ec2.SecurityGroup(this, 'AlbSecurityGroup', {
      vpc: this.vpc,
      description: 'Security group for Avalon ALB',
      allowAllOutbound: true,
    });

    albSecurityGroup.addIngressRule(
      ec2.Peer.anyIpv4(),
      ec2.Port.tcp(80),
      'Allow HTTP traffic'
    );

    albSecurityGroup.addIngressRule(
      ec2.Peer.anyIpv4(),
      ec2.Port.tcp(443),
      'Allow HTTPS traffic'
    );

    this.loadBalancer = new elbv2.ApplicationLoadBalancer(this, 'AvalonLoadBalancer', {
      vpc: this.vpc,
      internetFacing: true,
      securityGroup: albSecurityGroup,
    });

    const taskExecutionRole = new iam.Role(this, 'EcsTaskExecutionRole', {
      assumedBy: new iam.ServicePrincipal('ecs-tasks.amazonaws.com'),
      managedPolicies: [
        iam.ManagedPolicy.fromAwsManagedPolicyName('service-role/AmazonECSTaskExecutionRolePolicy'),
      ],
    });

    const ecsTaskSecurityGroup = new ec2.SecurityGroup(this, 'EcsTaskSecurityGroup', {
      vpc: this.vpc,
      description: 'Security group for Avalon ECS tasks',
      allowAllOutbound: true,
    });

    ecsTaskSecurityGroup.addIngressRule(
      ec2.Peer.ipv4(this.vpc.vpcCidrBlock),
      ec2.Port.allTcp(),
      'Allow all TCP traffic within VPC'
    );

    const serviceRegistryLogGroup = new logs.LogGroup(this, 'ServiceRegistryLogGroup', {
      logGroupName: '/ecs/avalon-service-registry',
      retention: logs.RetentionDays.ONE_MONTH,
      removalPolicy: cdk.RemovalPolicy.DESTROY,
    });

    const serviceRegistryTaskDefinition = new ecs.FargateTaskDefinition(this, 'ServiceRegistryTaskDef', {
      memoryLimitMiB: 1024,
      cpu: 512,
      executionRole: taskExecutionRole,
    });

    serviceRegistryTaskDefinition.addContainer('ServiceRegistryContainer', {
      image: ecs.ContainerImage.fromEcrRepository(this.ecrRepositories.serviceRegistry),
      portMappings: [{ containerPort: 8761 }],
      logging: ecs.LogDrivers.awsLogs({
        logGroup: serviceRegistryLogGroup,
        streamPrefix: 'service-registry',
      }),
      environment: {
        'SPRING_PROFILES_ACTIVE': 'prod',
      },
    });

    const serviceRegistryService = new ecs.FargateService(this, 'ServiceRegistryService', {
      cluster: this.cluster,
      taskDefinition: serviceRegistryTaskDefinition,
      desiredCount: 1,
      securityGroups: [ecsTaskSecurityGroup],
      assignPublicIp: false,
      vpcSubnets: {
        subnetType: ec2.SubnetType.PRIVATE_WITH_EGRESS,
      },
    });

    const apiGatewayTargetGroup = new elbv2.ApplicationTargetGroup(this, 'ApiGatewayTargetGroup', {
      vpc: this.vpc,
      port: 8080,
      protocol: elbv2.ApplicationProtocol.HTTP,
      targetType: elbv2.TargetType.IP,
      healthCheck: {
        path: '/actuator/health',
        interval: cdk.Duration.seconds(30),
        timeout: cdk.Duration.seconds(5),
        healthyThresholdCount: 3,
        unhealthyThresholdCount: 3,
      },
    });

    const httpListener = this.loadBalancer.addListener('HttpListener', {
      port: 80,
      open: true,
    });

    httpListener.addTargetGroups('ApiGatewayTargetGroup', {
      targetGroups: [apiGatewayTargetGroup],
    });

    new cdk.CfnOutput(this, 'FrontendUrl', {
      value: `https://${this.frontendDistribution.distributionDomainName}`,
      description: 'URL for the frontend application',
    });
    
    new cdk.CfnOutput(this, 'FrontendDistributionId', {
      value: this.frontendDistribution.distributionId,
      description: 'CloudFront Distribution ID for the frontend',
      exportName: 'AvalonFrontendDistributionId',
    });

    new cdk.CfnOutput(this, 'ApiGatewayUrl', {
      value: `http://${this.loadBalancer.loadBalancerDnsName}`,
      description: 'URL for the API Gateway',
    });

    new cdk.CfnOutput(this, 'DatabaseEndpoint', {
      value: this.database.dbInstanceEndpointAddress,
      description: 'Endpoint for the RDS database',
    });

    new cdk.CfnOutput(this, 'EcrRepositories', {
      value: JSON.stringify(Object.entries(this.ecrRepositories).reduce((acc, [key, repo]) => {
        acc[key] = repo.repositoryUri;
        return acc;
      }, {} as { [key: string]: string })),
      description: 'ECR Repository URIs',
    });
  }
}
