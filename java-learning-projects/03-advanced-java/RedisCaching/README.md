# RedisCaching Project

This project demonstrates how to use Redis as a cache in a Spring Boot application.

## Business Logic / Explanation

- **@EnableCaching:**
  - Enables caching in the Spring Boot application.
- **@Cacheable:**
  - The `/expensive/{input}` endpoint simulates an expensive operation (3-second delay) and caches the result in Redis.
  - Subsequent requests with the same input return instantly from cache.
- **How it works:**
  - The first request for an input takes 3 seconds; repeated requests are instant.

## How to Run
1. Ensure Redis is running locally (default port 6379).
2. Add the following dependencies to your `pom.xml`:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-redis</artifactId>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-cache</artifactId>
   </dependency>
   ```
3. Compile and run the application:
   ```
   mvn spring-boot:run
   ```
4. Test by accessing:
   ```
   curl http://localhost:8080/expensive/test
   ```
   - First request: ~3 seconds
   - Subsequent requests: instant

Expected output:
```
Processed: test
```

---

This example shows how caching can dramatically improve backend performance by reducing expensive computations and database calls.
