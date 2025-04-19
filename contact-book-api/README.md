# Contact Book API

## Description
A backend API for managing contacts, searching, and importing/exporting via CSV.

## Features
- CRUD operations for contacts
- Search contacts
- Import/export contacts via CSV
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
- `/api/contacts` - CRUD for contacts
- `/api/import` - Import contacts from CSV
- `/api/export` - Export contacts to CSV

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
