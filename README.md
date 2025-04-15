# Java Projects Repository

Welcome to your comprehensive Java projects repository! This guide will help you understand, build, and run all projects in this folder, regardless of your experience level.

---

## 📁 Project Overview

### Beginner
- **Library Management System:** Manage books, users, and lending records (Spring Boot, JPA, H2, JWT).
- **Task Manager:** CRUD task management app (Spring Boot, Maven).

### Intermediate
- **E-commerce Platform:** Full-featured online shop (Spring Boot, PostgreSQL, Redis, RabbitMQ, Docker, Stripe).

### Advanced
- **Cloud Platform:** Microservices platform (Spring Boot, Kafka, Eureka, Gateway, OAuth2).
- **Trading Engine:** High-performance trading core (Java, Maven).

### School
- **University:** Java code in `school/university/src`.
- **Standalone Java files:** `Main.java`, `Monday.java`, `Restaurant.java`, `Wedclass.java` in `school/`.

---

## 🛠️ Prerequisites
- **Java JDK:** 11 or newer ([Download](https://adoptium.net/))
- **Maven:** 3.6+ ([Download](https://maven.apache.org/download.cgi))
- **Docker:** (For e-commerce platform, [Download](https://www.docker.com/products/docker-desktop/))

---

## 🚀 How to Build & Run

### 1. Maven Projects (Most Subfolders)
#### Build
```bash
mvn clean install
```
#### Run (Spring Boot projects)
```bash
mvn spring-boot:run
```
- Run these commands inside the project folder (e.g., `cd beginner/library_management`)

#### Run Tests
```bash
mvn test
```

### 2. Dockerized Project (E-commerce Platform)
#### Build & Run with Docker Compose
```bash
cd intermediate/ecommerce_platform
# Build and run all services
docker-compose up --build
```
- Make sure Docker Desktop is running.

### 3. Standalone Java Files
#### Compile & Run
```bash
cd school
javac Main.java
java Main
```
- Replace `Main` with the file you want to run (e.g., `Monday`, `Restaurant`).

---

## 📚 Project Summaries

### Library Management System
- **Features:** Book/user management, lending, returns, fines, reservations, categories, search.
- **Tech:** Spring Boot, Spring Security, JPA, H2, JWT, Maven.
- **Run:** `cd beginner/library_management && mvn spring-boot:run`

### Task Manager
- **Features:** CRUD for tasks, simple demonstration app.
- **Tech:** Spring Boot, Maven.
- **Run:** `cd beginner/task_manager && mvn spring-boot:run`

### E-commerce Platform
- **Features:** Products, cart, orders, payments (Stripe), user auth, reviews, admin, analytics.
- **Tech:** Spring Boot, PostgreSQL, Redis, RabbitMQ, Docker, Stripe, JUnit.
- **Run:** `cd intermediate/ecommerce_platform && docker-compose up --build`

### Cloud Platform
- **Features:** Service discovery (Eureka), API Gateway, circuit breaking, tracing, Kafka events, OAuth2.
- **Tech:** Spring Boot, Spring Cloud, Kafka, Maven.
- **Run:** `cd advanced/cloud_platform && mvn spring-boot:run`

### Trading Engine
- **Features:** Ultra-low latency order matching, risk management, multi-asset support.
- **Tech:** Java, Maven.
- **Note:** This is a core library; integrate into a Spring Boot app to use.
- **Build:** `cd advanced/trading_engine && mvn clean install`

### School/University
- **Features:** Java exercises and university code.
- **Run:**
  - For `school/university`: Compile and run files in `src/`
  - For standalone files: See "Standalone Java Files" above

---

## 📝 Troubleshooting
- **Maven not found:** Make sure Maven is installed and added to your PATH.
- **Java version issues:** Run `java -version` and `mvn -version` to check.
- **Docker issues:** Ensure Docker Desktop is running and you have enough memory allocated.
- **Port conflicts:** Stop any services using required ports (e.g., 8080).

---

## 🤝 Contributions & Feedback
Feel free to explore, modify, and contribute to these projects. Suggestions and improvements are always welcome!

---

## 📖 Further Reading
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Maven Docs](https://maven.apache.org/guides/)
- [Docker Docs](https://docs.docker.com/)

---

Happy coding!


This repository contains a collection of Java projects that I have built to explore and demonstrate various concepts, frameworks, and tools in Java development. The projects are organized into different levels of complexity and domains, showcasing a wide range of use cases.

## Project Structure

### Beginner
- **Library Management System**: A simple application to manage books, users, and lending records using Spring Boot and JPA.
- **Task Manager**: A task management application with CRUD operations and data seeding for demonstration purposes.

### Intermediate
- **E-commerce Platform**: A comprehensive e-commerce application featuring product management, order processing, and integration with RabbitMQ, Redis, and Swagger for API documentation.

### Advanced
- **Cloud Platform**: A cloud-based event streaming service using Kafka and Spring Cloud Stream for real-time data processing.
- **Trading Engine**: A high-performance trading engine for simulating financial transactions and order matching.

## Key Features
- **Spring Boot**: Most projects leverage Spring Boot for rapid development and dependency injection.
- **Database Integration**: Projects use JPA/Hibernate for database operations.
- **Messaging**: RabbitMQ and Kafka are used for asynchronous communication in intermediate and advanced projects.
- **Caching**: Redis is integrated for caching frequently accessed data.
- **API Documentation**: Swagger/OpenAPI is used for documenting RESTful APIs.
- **Testing**: Unit and integration tests are included in some projects to ensure code quality.

## How to Run
Each project has its own `README.md` or documentation file with instructions on how to set up and run the application. Most projects use Maven for dependency management and build automation.

## Future Plans
- Add more advanced projects, such as a microservices architecture with service discovery and load balancing.
- Explore other Java frameworks like Quarkus and Micronaut.
- Implement CI/CD pipelines for automated testing and deployment.

Feel free to explore the projects and provide feedback or suggestions!