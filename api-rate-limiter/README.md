# API Rate Limiter

## Description
A backend service for API rate limiting with configurable policies per user/IP.

## Features
- Rate limit API requests
- Configurable policies per user/IP
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Redis/PostgreSQL

## Setup Instructions
1. Install Java and Maven
2. Configure Redis/database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/ratelimit` - Manage rate limiting policies

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
