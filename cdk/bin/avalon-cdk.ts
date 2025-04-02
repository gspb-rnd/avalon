#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from 'aws-cdk-lib';
import { AvalonInfrastructureStack } from '../lib/avalon-infrastructure-stack';
import { AvalonPipelineStack } from '../lib/avalon-pipeline-stack';

const app = new cdk.App();

new AvalonInfrastructureStack(app, 'AvalonInfrastructureStack', {
  env: { 
    account: process.env.CDK_DEFAULT_ACCOUNT, 
    region: process.env.CDK_DEFAULT_REGION || 'us-east-1' 
  },
});

new AvalonPipelineStack(app, 'AvalonPipelineStack', {
  env: { 
    account: process.env.CDK_DEFAULT_ACCOUNT, 
    region: process.env.CDK_DEFAULT_REGION || 'us-east-1' 
  },
});

app.synth();
