# Cloud Platform

A cloud-native microservices platform built with Spring Boot and Spring Cloud.

## Features
- Service Discovery (Eureka)
- API Gateway (Spring Cloud Gateway)
- Circuit Breaking (Resilience4j)
- Distributed Tracing (Sleuth + Zipkin)
- Event Streaming (Kafka)
- Reactive Programming (WebFlux)
- OAuth2 Security

## Prerequisites
- Java 11+
- Maven 3.6+

## Build & Run
```bash
cd cloud_platform
mvn clean install
mvn spring-boot:run
```

## Configuration
Edit `src/main/resources/application.yml` for service configuration.

## Structure
- `config/` - Security and platform configuration
- `controller/` - REST controllers
- `service/` - Business logic and integrations

## License
MIT
