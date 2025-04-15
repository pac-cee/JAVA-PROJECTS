# SpringBootRestAPI Project

This project demonstrates how to build a simple REST API using Spring Boot.

## Business Logic / Explanation

- **Spring Boot Application:**
  - Annotated with `@SpringBootApplication` to enable auto-configuration and component scanning.
- **REST Controller:**
  - Annotated with `@RestController` to handle HTTP requests.
  - Defines a GET endpoint `/hello` that returns a greeting string.
- **How it works:**
  - When you access `/hello`, the API responds with `Hello from Spring Boot REST API!`.

## How to Run
1. Ensure you have Java and Maven installed.
2. Create a `pom.xml` with Spring Boot dependencies (see below).
3. Compile and run the application:
   ```
   mvn spring-boot:run
   ```
4. Visit [http://localhost:8080/hello](http://localhost:8080/hello) in your browser or use curl:
   ```
   curl http://localhost:8080/hello
   ```

Expected output:
```
Hello from Spring Boot REST API!
```

### Sample pom.xml dependencies:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

---

This is the foundation for building modern RESTful services in Java.
