# ETL Pipeline Backend

## Description
A backend API for orchestrating ETL (Extract, Transform, Load) pipelines and managing data workflows.

## Features
- Define and manage ETL pipelines
- Monitor ETL jobs
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Batch
- PostgreSQL/MySQL

## Setup Instructions
1. Install Java and Maven
2. Configure database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/pipelines` - Manage ETL pipelines
- `/api/jobs` - Monitor ETL jobs

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
