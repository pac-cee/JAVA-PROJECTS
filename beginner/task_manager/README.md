# Task Manager API

## Description
A beginner-friendly Spring Boot application for managing tasks with a RESTful API. This project is perfect for learning Java and Spring Boot fundamentals.

## Features
- Create, Read, Update, and Delete tasks
- Mark tasks as completed/incomplete
- Filter tasks by status
- Sort tasks by creation date
- Task categories and priority levels
- Due date management
- Task search functionality
- Basic authentication
- Swagger API documentation

## Technologies Used
- Java 11
- Spring Boot 2.7.18
- Spring Data JPA
- Spring Security (Basic Auth)
- H2 Database (development)
- MySQL (production)
- Swagger/OpenAPI
- Lombok
- JUnit 5 & Mockito
- Maven

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/example/taskmanager/
│   │       ├── config/
│   │       │   ├── SwaggerConfig.java
│   │       │   └── SecurityConfig.java
│   │       ├── controller/
│   │       │   └── TaskController.java
│   │       ├── model/
│   │       │   ├── Task.java
│   │       │   └── Category.java
│   │       ├── repository/
│   │       │   └── TaskRepository.java
│   │       ├── service/
│   │       │   └── TaskService.java
│   │       └── TaskManagerApplication.java
│   └── resources/
│       ├── application.properties
│       └── data.sql
└── test/
    └── java/
        └── com/example/taskmanager/
            ├── controller/
            ├── service/
            └── repository/
```

## Setup Instructions for Beginners
1. Prerequisites:
   - Install Java JDK 11 or higher
   - Install Maven
   - Install your favorite IDE (recommended: IntelliJ IDEA or VS Code)

2. Clone the repository:
   ```bash
   git clone <repository-url>
   cd task-manager
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the application:
   - API: http://localhost:8080/api/tasks
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - H2 Console: http://localhost:8080/h2-console

## API Endpoints
- GET `/api/tasks` - Get all tasks
- GET `/api/tasks/{id}` - Get task by ID
- POST `/api/tasks` - Create new task
- PUT `/api/tasks/{id}` - Update task
- DELETE `/api/tasks/{id}` - Delete task
- GET `/api/tasks/completed` - Get completed tasks
- GET `/api/tasks/incomplete` - Get incomplete tasks
- GET `/api/tasks/search?query={query}` - Search tasks
- GET `/api/tasks/category/{category}` - Get tasks by category

## Learning Points
1. Spring Boot Basics
   - Application setup
   - Configuration
   - Dependency injection

2. REST API Development
   - HTTP methods
   - Response status codes
   - Request/Response handling

3. Database Operations
   - JPA/Hibernate basics
   - CRUD operations
   - Query methods

4. Testing
   - Unit testing
   - Integration testing
   - Mockito framework

5. API Documentation
   - Swagger/OpenAPI
   - API versioning

## Common Issues & Solutions
1. Application won't start
   - Check if port 8080 is free
   - Verify Java version
   - Check application.properties configuration

2. Database connection issues
   - Verify H2 console settings
   - Check database credentials
   - Confirm database URL

## Next Steps for Learning
1. Add user authentication with JWT
2. Implement task sharing between users
3. Add email notifications
4. Create a frontend application
5. Deploy to a cloud platform

## Contributing
Feel free to fork and submit pull requests. For major changes:
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Open a pull request

## License
MIT License - feel free to use this project for learning purposes.
