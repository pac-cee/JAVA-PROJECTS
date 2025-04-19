# Social Media Backend

## Description
A backend API for a social media platform with posts, likes, comments, followers, and notifications.

## Features
- CRUD operations for posts and comments
- Likes and follower management
- Real-time notifications
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- MongoDB/PostgreSQL
- RabbitMQ (for notifications)

## Setup Instructions
1. Install Java and Maven
2. Configure database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/posts` - CRUD for posts
- `/api/comments` - CRUD for comments
- `/api/likes` - Manage likes
- `/api/followers` - Manage followers
- `/api/notifications` - Real-time notifications

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
