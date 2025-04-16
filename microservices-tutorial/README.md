# Microservices in Java with Spring Boot

## Why Java for Backend?
- **Platform Independent:** Runs on any OS with the JVM.
- **Mature Ecosystem:** Rich libraries, frameworks (like Spring), and tools.
- **Performance:** Optimized for high concurrency and scalability.
- **Security:** Strong built-in security features.
- **Community:** Large support community and extensive documentation.

## What are Microservices?
- **Microservices** is an architectural style where applications are composed of small, independent services that communicate over APIs.
- Each service focuses on a specific business capability.
- Benefits: easier to scale, develop, and maintain.

## Project Structure
This tutorial contains:
- `user-service`: Handles user-related operations.
- `order-service`: Handles order-related operations.
- `discovery-service`: Service registry using Eureka for microservice discovery.
- (Optional) `api-gateway`: Unified entry point for all services.

Each service is a Spring Boot project with a simple REST endpoint.

## How to Use
1. Open each service in your IDE (e.g., VSCode, IntelliJ).
2. Build and run the services.
3. Test the endpoints using Postman or your browser.

---

Continue reading each service's README and code for more details and learning steps.
