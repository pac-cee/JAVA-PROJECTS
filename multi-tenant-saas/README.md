# Multi-Tenant SaaS Backend

## Description
A backend API for a multi-tenant SaaS application, supporting tenant isolation and user management.

## Features
- Tenant registration and isolation
- User management per tenant
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL/MongoDB

## Setup Instructions
1. Install Java and Maven
2. Configure database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/tenants` - Manage tenants
- `/api/users` - User management

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
