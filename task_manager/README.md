# README.md

# Task Manager Application

Welcome to the Task Manager Application! This project is designed for those who are learning Java and want to explore the basics of building a Spring Boot application.

## Project Overview

The Task Manager Application is a simple task management tool that allows users to create, read, update, and delete tasks. It uses an in-memory H2 database for data storage and provides a RESTful API for interaction.

## Project Structure

```
task_manager
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── taskmanager
│       │               ├── config
│       │               ├── controller
│       │               ├── model
│       │               ├── repository
│       │               └── TaskManagerApplication.java
│       └── resources
│           ├── application.properties
│           └── data.sql
├── .mvn
│   └── wrapper
│       └── maven-wrapper.properties
├── pom.xml
└── README.md
```

## Getting Started

To get started with the Task Manager Application, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd task_manager
   ```

2. **Build the project**:
   Make sure you have Maven installed. Run the following command to build the project:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   You can run the application using the following command:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**:
   Once the application is running, you can access it at `http://localhost:8080`.

## Configuration

The application configuration can be found in the `src/main/resources/application.properties` file. You can modify the database connection settings and other configurations as needed.

## Database Initialization

The `src/main/resources/data.sql` file contains SQL statements that will be executed on application startup to initialize the database with sample data.

## Contributing

Feel free to contribute to this project by submitting issues or pull requests. Your contributions are welcome!

## License

This project is licensed under the MIT License. See the LICENSE file for more details.