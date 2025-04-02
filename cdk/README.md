# Avalon Loan Origination System - AWS CDK Pipeline

This directory contains the AWS CDK configuration for the CI/CD pipeline of the Avalon Loan Origination System.

## Architecture

The CI/CD pipeline is built using AWS CDK and consists of the following components:

1. **Infrastructure Stack** - Provisions the AWS resources needed for the application:
   - VPC with public and private subnets
   - ECR repositories for Docker images
   - ECS cluster for running microservices
   - RDS PostgreSQL database
   - S3 bucket for frontend hosting
   - CloudFront distribution for frontend delivery
   - Application Load Balancer for API Gateway

2. **Pipeline Stack** - Implements the CI/CD pipeline:
   - CodePipeline for orchestration
   - CodeBuild projects for building and testing
   - Deployment to ECS services
   - Frontend deployment to S3/CloudFront

## Prerequisites

- AWS CLI configured with appropriate credentials
- Node.js 16.x or later
- AWS CDK CLI installed (`npm install -g aws-cdk`)

## Setup

1. Install dependencies:
   ```
   npm install
   ```

2. Bootstrap the AWS environment (only needed once per AWS account/region):
   ```
   cdk bootstrap
   ```

3. Deploy the infrastructure stack:
   ```
   cdk deploy AvalonInfrastructureStack
   ```

4. Deploy the pipeline stack:
   ```
   cdk deploy AvalonPipelineStack
   ```

## Pipeline Workflow

The CI/CD pipeline follows this workflow:

1. **Source Stage**: Pulls code from the GitHub repository
2. **Build Stage**:
   - Backend Build: Builds Java microservices, runs tests, creates Docker images, and pushes to ECR
   - Frontend Build: Builds React.js application and deploys to S3
3. **Deploy Stage**: Deploys the microservices to ECS and invalidates CloudFront cache

## Customization

You can customize the pipeline by modifying the following files:

- `lib/avalon-infrastructure-stack.ts`: Infrastructure resources
- `lib/avalon-pipeline-stack.ts`: CI/CD pipeline configuration

## Secrets Management

The pipeline requires the following secrets:

1. GitHub token stored in AWS Secrets Manager as `github-token`
2. Database credentials are automatically generated and stored securely

## Monitoring

The pipeline provides the following monitoring capabilities:

- CloudWatch Logs for all services
- CloudWatch Alarms for critical metrics
- Pipeline execution history in the AWS Console

## Troubleshooting

If you encounter issues with the pipeline:

1. Check the CodeBuild logs for build failures
2. Verify that the GitHub token has the correct permissions
3. Ensure that the ECR repositories exist and are accessible
4. Check the ECS service deployment status
