# Task Manager API

## Description
A RESTful API for managing tasks with user authentication, search, and filtering capabilities.

## Features
- CRUD operations for tasks
- User authentication (JWT)
- Search and filter tasks
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- H2/MySQL
- JWT

## Setup Instructions
1. Install Java and Maven
2. Configure database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/tasks` - CRUD for tasks
- `/api/auth` - Authentication endpoints

## Architecture
Simple layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
