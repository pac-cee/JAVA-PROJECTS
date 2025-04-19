# Chat API

## Description
A backend API for real-time chat with message history and user management.

## Features
- Real-time messaging (WebSocket)
- Message history
- User management
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- WebSocket
- PostgreSQL

## Setup Instructions
1. Install Java and Maven
2. Configure database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/messages` - Send/receive messages
- `/api/users` - User management

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
