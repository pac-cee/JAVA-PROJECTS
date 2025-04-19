# Serverless Functions Backend

## Description
A backend API for managing and executing serverless functions as a service.

## Features
- Deploy and execute serverless functions
- Function versioning
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- AWS Lambda/LocalStack

## Setup Instructions
1. Install Java and Maven
2. Configure AWS/LocalStack in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/functions` - Manage functions
- `/api/execute` - Execute functions

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
