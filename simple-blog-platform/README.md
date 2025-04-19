# Simple Blog Platform

## Description
A basic blogging platform backend with posts, comments, and user roles (admin, author, reader).

## Features
- CRUD operations for posts and comments
- User roles (admin, author, reader)
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL

## Setup Instructions
1. Install Java and Maven
2. Configure database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/posts` - CRUD for posts
- `/api/comments` - CRUD for comments
- `/api/auth` - Authentication endpoints

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
