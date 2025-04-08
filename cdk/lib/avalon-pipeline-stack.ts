import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as codebuild from 'aws-cdk-lib/aws-codebuild';
import * as codepipeline from 'aws-cdk-lib/aws-codepipeline';
import * as codepipeline_actions from 'aws-cdk-lib/aws-codepipeline-actions';
import * as iam from 'aws-cdk-lib/aws-iam';
import * as s3 from 'aws-cdk-lib/aws-s3';
import * as ecr from 'aws-cdk-lib/aws-ecr';
import * as ecs from 'aws-cdk-lib/aws-ecs';
import * as ec2 from 'aws-cdk-lib/aws-ec2';

export class AvalonPipelineStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const artifactBucket = new s3.Bucket(this, 'ArtifactBucket', {
      removalPolicy: cdk.RemovalPolicy.DESTROY,
      autoDeleteObjects: true,
      encryption: s3.BucketEncryption.S3_MANAGED,
    });

    const pipeline = new codepipeline.Pipeline(this, 'AvalonPipeline', {
      pipelineName: 'AvalonPipeline',
      artifactBucket: artifactBucket,
      restartExecutionOnUpdate: true,
    });

    const sourceOutput = new codepipeline.Artifact('SourceCode');
    
    const sourceAction = new codepipeline_actions.GitHubSourceAction({
      actionName: 'GitHub_Source',
      owner: 'gspb-rnd',
      repo: 'avalon',
      branch: 'master-branch',
      oauthToken: cdk.SecretValue.secretsManager('github-token'),
      output: sourceOutput,
      trigger: codepipeline_actions.GitHubTrigger.WEBHOOK,
    });

    pipeline.addStage({
      stageName: 'Source',
      actions: [sourceAction],
    });

    const backendBuildOutput = new codepipeline.Artifact('BackendBuildOutput');
    
    const backendBuildProject = new codebuild.PipelineProject(this, 'BackendBuild', {
      environment: {
        buildImage: codebuild.LinuxBuildImage.AMAZON_LINUX_2_4,
        privileged: true,
      },
      environmentVariables: {
        AWS_ACCOUNT_ID: {
          value: cdk.Aws.ACCOUNT_ID,
        },
        AWS_REGION: {
          value: cdk.Aws.REGION,
        },
      },
      buildSpec: codebuild.BuildSpec.fromObject({
        version: '0.2',
        phases: {
          install: {
            'runtime-versions': {
              java: 'corretto17',
            },
            commands: [
              'echo Installing Maven...',
              'yum install -y maven',
            ],
          },
          pre_build: {
            commands: [
              'echo Logging in to Amazon ECR...',
              'aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com',
              'cd backend',
              'echo Running tests...',
              'mvn test',
            ],
          },
          build: {
            commands: [
              'echo Building the backend services...',
              'cd service-registry && mvn clean package -DskipTests && cd ..',
              'cd api-gateway && mvn clean package -DskipTests && cd ..',
              'cd client-service && mvn clean package -DskipTests && cd ..',
              'cd loan-application-service && mvn clean package -DskipTests && cd ..',
              'cd document-service && mvn clean package -DskipTests && cd ..',
              'cd workflow-service && mvn clean package -DskipTests && cd ..',
              'echo Building Docker images...',
              'cd service-registry && docker build -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-service-registry:latest . && cd ..',
              'cd api-gateway && docker build -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-api-gateway:latest . && cd ..',
              'cd client-service && docker build -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-client-service:latest . && cd ..',
              'cd loan-application-service && docker build -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-loan-application-service:latest . && cd ..',
              'cd document-service && docker build -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-document-service:latest . && cd ..',
              'cd workflow-service && docker build -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-workflow-service:latest . && cd ..',
              'echo Pushing Docker images to ECR...',
              'docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-service-registry:latest',
              'docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-api-gateway:latest',
              'docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-client-service:latest',
              'docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-loan-application-service:latest',
              'docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-document-service:latest',
              'docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-workflow-service:latest',
            ],
          },
          post_build: {
            commands: [
              'echo Creating deployment artifacts...',
              'echo "{\"ImageURI\":\"$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-service-registry:latest\"}" > service-registry-imageDefinitions.json',
              'echo "{\"ImageURI\":\"$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-api-gateway:latest\"}" > api-gateway-imageDefinitions.json',
              'echo "{\"ImageURI\":\"$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-client-service:latest\"}" > client-service-imageDefinitions.json',
              'echo "{\"ImageURI\":\"$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-loan-application-service:latest\"}" > loan-application-service-imageDefinitions.json',
              'echo "{\"ImageURI\":\"$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-document-service:latest\"}" > document-service-imageDefinitions.json',
              'echo "{\"ImageURI\":\"$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/avalon-workflow-service:latest\"}" > workflow-service-imageDefinitions.json',
            ],
          },
        },
        artifacts: {
          files: [
            'service-registry-imageDefinitions.json',
            'api-gateway-imageDefinitions.json',
            'client-service-imageDefinitions.json',
            'loan-application-service-imageDefinitions.json',
            'document-service-imageDefinitions.json',
            'workflow-service-imageDefinitions.json',
          ],
        },
      }),
    });

    backendBuildProject.addToRolePolicy(
      new iam.PolicyStatement({
        effect: iam.Effect.ALLOW,
        actions: [
          'ecr:GetAuthorizationToken',
          'ecr:BatchCheckLayerAvailability',
          'ecr:GetDownloadUrlForLayer',
          'ecr:GetRepositoryPolicy',
          'ecr:DescribeRepositories',
          'ecr:ListImages',
          'ecr:DescribeImages',
          'ecr:BatchGetImage',
          'ecr:InitiateLayerUpload',
          'ecr:UploadLayerPart',
          'ecr:CompleteLayerUpload',
          'ecr:PutImage',
        ],
        resources: ['*'],
      })
    );

    const backendBuildAction = new codepipeline_actions.CodeBuildAction({
      actionName: 'BuildBackend',
      project: backendBuildProject,
      input: sourceOutput,
      outputs: [backendBuildOutput],
    });

    const frontendBuildOutput = new codepipeline.Artifact('FrontendBuildOutput');
    
    const frontendBuildProject = new codebuild.PipelineProject(this, 'FrontendBuild', {
      environment: {
        buildImage: codebuild.LinuxBuildImage.STANDARD_5_0,
      },
      buildSpec: codebuild.BuildSpec.fromObject({
        version: '0.2',
        phases: {
          install: {
            'runtime-versions': {
              nodejs: '16',
            },
            commands: [
              'echo Installing dependencies...',
              'cd frontend/shared-components',
              'npm install --legacy-peer-deps',
              'npm run build',
              'cd ../advisor-portal',
              'npm install --legacy-peer-deps',
            ],
          },
          build: {
            commands: [
              'echo Building the frontend...',
              'cd frontend/advisor-portal',
              'REACT_APP_API_URL=https://api.avalon.example.com npm run build',
            ],
          },
          post_build: {
            commands: [
              'echo Preparing frontend artifacts...',
              'cd frontend/advisor-portal',
              'aws s3 sync build/ s3://avalon-frontend-$AWS_ACCOUNT_ID-$AWS_REGION/ --delete',
              'aws cloudfront create-invalidation --distribution-id $CLOUDFRONT_DISTRIBUTION_ID --paths "/*"',
            ],
          },
        },
        artifacts: {
          'base-directory': 'frontend/advisor-portal/build',
          files: ['**/*'],
        },
      }),
      environmentVariables: {
        AWS_ACCOUNT_ID: {
          value: cdk.Aws.ACCOUNT_ID,
        },
        AWS_REGION: {
          value: cdk.Aws.REGION,
        },
        CLOUDFRONT_DISTRIBUTION_ID: {
          value: cdk.Fn.importValue('AvalonFrontendDistributionId'),
        },
      },
    });

    frontendBuildProject.addToRolePolicy(
      new iam.PolicyStatement({
        effect: iam.Effect.ALLOW,
        actions: [
          's3:PutObject',
          's3:GetObject',
          's3:DeleteObject',
          's3:ListBucket',
        ],
        resources: [
          `arn:aws:s3:::avalon-frontend-${this.account}-${this.region}`,
          `arn:aws:s3:::avalon-frontend-${this.account}-${this.region}/*`,
        ],
      })
    );

    frontendBuildProject.addToRolePolicy(
      new iam.PolicyStatement({
        effect: iam.Effect.ALLOW,
        actions: [
          'cloudfront:CreateInvalidation',
        ],
        resources: ['*'],
      })
    );

    const frontendBuildAction = new codepipeline_actions.CodeBuildAction({
      actionName: 'BuildFrontend',
      project: frontendBuildProject,
      input: sourceOutput,
      outputs: [frontendBuildOutput],
    });

    pipeline.addStage({
      stageName: 'Build',
      actions: [backendBuildAction, frontendBuildAction],
    });

    const deployStage = pipeline.addStage({
      stageName: 'Deploy',
    });

    const serviceRegistryRepo = ecr.Repository.fromRepositoryName(
      this, 'ServiceRegistryRepo', 'avalon-service-registry'
    );

    const apiGatewayRepo = ecr.Repository.fromRepositoryName(
      this, 'ApiGatewayRepo', 'avalon-api-gateway'
    );

    const clientServiceRepo = ecr.Repository.fromRepositoryName(
      this, 'ClientServiceRepo', 'avalon-client-service'
    );

    const loanApplicationServiceRepo = ecr.Repository.fromRepositoryName(
      this, 'LoanApplicationServiceRepo', 'avalon-loan-application-service'
    );

    const documentServiceRepo = ecr.Repository.fromRepositoryName(
      this, 'DocumentServiceRepo', 'avalon-document-service'
    );

    const workflowServiceRepo = ecr.Repository.fromRepositoryName(
      this, 'WorkflowServiceRepo', 'avalon-workflow-service'
    );

    const vpc = ec2.Vpc.fromLookup(this, 'AvalonVpc', {
      vpcId: 'vpc-01ebdf350623e9bf9'
    });

    const cluster = ecs.Cluster.fromClusterAttributes(this, 'AvalonCluster', {
      clusterName: 'AvalonInfrastructureStack-AvalonClusterEA8F32A6-sguXCCUBpT7p',
      vpc: vpc,
      securityGroups: []
    });

    const serviceRegistryService = ecs.FargateService.fromFargateServiceAttributes(this, 'ServiceRegistryService', {
      cluster,
      serviceName: 'avalon-service-registry',
    });

    const apiGatewayService = ecs.FargateService.fromFargateServiceAttributes(this, 'ApiGatewayService', {
      cluster,
      serviceName: 'avalon-api-gateway',
    });

    const clientService = ecs.FargateService.fromFargateServiceAttributes(this, 'ClientService', {
      cluster,
      serviceName: 'avalon-client-service',
    });

    const loanApplicationService = ecs.FargateService.fromFargateServiceAttributes(this, 'LoanApplicationService', {
      cluster,
      serviceName: 'avalon-loan-application-service',
    });

    const documentService = ecs.FargateService.fromFargateServiceAttributes(this, 'DocumentService', {
      cluster,
      serviceName: 'avalon-document-service',
    });

    const workflowService = ecs.FargateService.fromFargateServiceAttributes(this, 'WorkflowService', {
      cluster,
      serviceName: 'avalon-workflow-service',
    });

    deployStage.addAction(
      new codepipeline_actions.EcsDeployAction({
        actionName: 'DeployServiceRegistry',
        service: serviceRegistryService,
        imageFile: backendBuildOutput.atPath('service-registry-imageDefinitions.json'),
      })
    );

    deployStage.addAction(
      new codepipeline_actions.EcsDeployAction({
        actionName: 'DeployApiGateway',
        service: apiGatewayService,
        imageFile: backendBuildOutput.atPath('api-gateway-imageDefinitions.json'),
      })
    );

    deployStage.addAction(
      new codepipeline_actions.EcsDeployAction({
        actionName: 'DeployClientService',
        service: clientService,
        imageFile: backendBuildOutput.atPath('client-service-imageDefinitions.json'),
      })
    );

    deployStage.addAction(
      new codepipeline_actions.EcsDeployAction({
        actionName: 'DeployLoanApplicationService',
        service: loanApplicationService,
        imageFile: backendBuildOutput.atPath('loan-application-service-imageDefinitions.json'),
      })
    );

    deployStage.addAction(
      new codepipeline_actions.EcsDeployAction({
        actionName: 'DeployDocumentService',
        service: documentService,
        imageFile: backendBuildOutput.atPath('document-service-imageDefinitions.json'),
      })
    );

    deployStage.addAction(
      new codepipeline_actions.EcsDeployAction({
        actionName: 'DeployWorkflowService',
        service: workflowService,
        imageFile: backendBuildOutput.atPath('workflow-service-imageDefinitions.json'),
      })
    );

    new cdk.CfnOutput(this, 'PipelineConsoleUrl', {
      value: `https://${cdk.Aws.REGION}.console.aws.amazon.com/codesuite/codepipeline/pipelines/${pipeline.pipelineName}/view?region=${cdk.Aws.REGION}`,
      description: 'URL to the CodePipeline console',
    });
  }
}
