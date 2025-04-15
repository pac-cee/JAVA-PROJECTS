# MicroservicesDemo Project

This project demonstrates a simple microservices architecture using Spring Boot.

## Business Logic / Explanation

- **UserService**
  - Exposes `/user/{id}` endpoint to return a user name by ID.
- **OrderService**
  - Exposes `/order/{orderId}` endpoint.
  - Calls UserService (via HTTP) to get user info and combines it with order info.
- **How it works:**
  - In a real microservices setup, each service would run in its own process, possibly on different machines, and communicate via REST APIs or messaging.
  - Here, for demonstration, UserService would run on port 8081 and OrderService on port 8080.

## How to Run
1. Create two Spring Boot applications: UserService and OrderService.
2. Start UserService on port 8081:
   - In `application.properties`: `server.port=8081`
3. Start OrderService on port 8080.
4. Access [http://localhost:8080/order/123](http://localhost:8080/order/123)

Expected output:
```
Order-123 by User-1
```

### Sample pom.xml dependencies:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

---

This example introduces the concept of distributed systems and service-to-service communication.
