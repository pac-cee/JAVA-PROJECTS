# Document Management System

## Description
A backend API for uploading, versioning, searching, and managing documents with access control.

## Features
- Upload and version documents
- Search documents
- Access control for users
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- S3/MinIO
- Elasticsearch

## Setup Instructions
1. Install Java and Maven
2. Configure storage and database in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/documents` - Upload/version/search documents
- `/api/access` - Manage access control

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
