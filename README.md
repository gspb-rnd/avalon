# Avalon - Loan Origination System

This repository contains the implementation of the Loan Origination System for high net worth customers, following the design documents in the [design branch](https://github.com/gspb-rnd/avalon/tree/devin/1743576044-loan-origination-design).

## Project Structure

### Backend Services

The backend follows a microservices architecture with domain-driven design:

- **Client Service**: Manages client information, KYC, and relationships
- **Loan Application Service**: Handles loan application lifecycle and validation
- **Collateral Service**: Manages collateral information and valuation
- **Document Service**: Handles document generation and management
- **Workflow Service**: Manages business processes and rule overrides
- **Audit Service**: Records all system activities for compliance
- **API Gateway**: Provides a unified entry point for all services

### Frontend

The frontend follows a micro-frontend architecture:

- **Advisor Portal**: Main application shell
- **Shared Components**: Reusable UI components across micro-frontends

## Technology Stack

### Backend
- Java with Spring Boot
- Spring Cloud for microservices
- PostgreSQL for relational data
- MongoDB for document storage
- Redis for caching
- Apache Kafka for event streaming

### Frontend
- React.js with TypeScript
- Redux for state management
- Material-UI for components
- Tailwind CSS for styling
- Module Federation for micro-frontend architecture

## Development Setup

Instructions for setting up the development environment will be added as implementation progresses.

## Deployment

The system is deployed on AWS using:
- Amazon ECS for container orchestration
- Amazon RDS for relational database
- Amazon DocumentDB for document storage
- Amazon ElastiCache for caching
- Amazon S3 for document storage
