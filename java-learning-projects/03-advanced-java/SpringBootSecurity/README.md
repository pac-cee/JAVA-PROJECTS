# SpringBootSecurity Project

This project demonstrates how to secure a Spring Boot REST API endpoint using Spring Security with basic authentication.

## Business Logic / Explanation

- **Spring Boot Application:**
  - `@SpringBootApplication` and `@RestController` enable REST endpoints.
- **Secured Endpoint:**
  - `/secure` endpoint requires authentication.
- **Security Configuration:**
  - `@EnableWebSecurity` and `SecurityFilterChain` configure HTTP Basic authentication for all requests.
  - Only authenticated users can access any endpoint.
- **How it works:**
  - When you access `/secure`, you are prompted for a username and password (default: `user` and a generated password in the console).

## How to Run
1. Ensure you have Java and Maven installed.
2. Create a `pom.xml` with Spring Boot and Spring Security dependencies (see below).
3. Compile and run the application:
   ```
   mvn spring-boot:run
   ```
4. Visit [http://localhost:8080/secure](http://localhost:8080/secure) in your browser.
5. Enter the username `user` and the generated password from the console.

Expected output:
```
This is a secured endpoint!
```

### Sample pom.xml dependencies:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

---

This example is the foundation for securing Java backend APIs in production systems.
