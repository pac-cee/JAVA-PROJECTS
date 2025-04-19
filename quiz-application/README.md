# Quiz Application

## Description
A backend API for creating and taking quizzes, with score calculation and reporting.

## Features
- Create quizzes
- Take quizzes
- Score calculation and reporting
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- H2/MySQL

## Setup Instructions
1. Install Java and Maven
2. Configure database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/quizzes` - CRUD for quizzes
- `/api/attempts` - Take quiz, get score

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
