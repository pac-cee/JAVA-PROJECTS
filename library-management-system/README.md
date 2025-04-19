# Library Management System

## Description
A backend API for managing books, borrowers, lending, returns, and late fees.

## Features
- CRUD operations for books and borrowers
- Lending and returning books
- Late fee calculation
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL

## Setup Instructions
1. Install Java and Maven
2. Configure database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/books` - CRUD for books
- `/api/borrowers` - CRUD for borrowers
- `/api/lendings` - Lend/return books

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
