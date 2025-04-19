# IoT Device Data Collector

## Description
A backend API for registering IoT devices, ingesting data, and performing analytics.

## Features
- Register and manage IoT devices
- Ingest device data
- Perform analytics on collected data
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Kafka
- TimescaleDB/PostgreSQL

## Setup Instructions
1. Install Java, Maven, and Kafka
2. Configure database and Kafka in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/devices` - Register/manage devices
- `/api/data` - Ingest/query data
- `/api/analytics` - Analytics endpoints

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
