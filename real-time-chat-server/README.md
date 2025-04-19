# Real-Time Chat Server

## Description
A backend API for real-time chat with WebSocket, message history, and user presence tracking.

## Features
- Real-time messaging (WebSocket)
- Message history
- User presence tracking
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- WebSocket
- Redis/PostgreSQL

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
