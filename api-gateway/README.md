# API Gateway

## Description
A backend API gateway for routing, load balancing, and securing microservices.

## Features
- Route requests to microservices
- Load balancing and rate limiting
- Security and authentication
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Cloud Gateway
- Redis/PostgreSQL

## Setup Instructions
1. Install Java and Maven
2. Configure gateway in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/*` - Proxy to microservices
- `/api/gateway` - Gateway management

## Architecture
API Gateway pattern with microservice routing.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
