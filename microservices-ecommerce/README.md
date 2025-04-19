# Microservices E-Commerce Platform

## Description
A microservices-based backend for an e-commerce platform, including product, order, payment, and user services.

## Features
- Product, order, payment, and user microservices
- API Gateway and service discovery
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Spring Cloud (Eureka, Zuul)
- Docker
- MySQL/PostgreSQL

## Setup Instructions
1. Install Java, Maven, and Docker
2. Configure services in `application.properties`
3. Run: `docker-compose up` or run each service with `mvn spring-boot:run`

## API Documentation
- `/api/products` - Product service
- `/api/orders` - Order service
- `/api/payments` - Payment service
- `/api/users` - User service

## Architecture
Microservices architecture with API Gateway and service discovery.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
