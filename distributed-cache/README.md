# Distributed Cache

## Description
A backend API for a distributed caching system to speed up data retrieval and reduce database load.

## Features
- Store and retrieve cached data
- Distributed architecture
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Redis/Apache Ignite

## Setup Instructions
1. Install Java and Maven
2. Configure Redis/Ignite in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/cache` - Store/retrieve cache data

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
