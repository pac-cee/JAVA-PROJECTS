# URL Shortener

## Description
A backend API for shortening URLs, redirecting, and tracking usage statistics.

## Features
- Shorten URLs
- Redirect to original URL
- Track usage statistics
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- Redis/MySQL

## Setup Instructions
1. Install Java and Maven
2. Configure database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/shorten` - Shorten URL
- `/api/{shortUrl}` - Redirect

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
