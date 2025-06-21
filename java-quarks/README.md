# User Management REST API

This is a simple Spring Boot REST API for user management. It stores user data in memory and provides endpoints to create and fetch users.

## Programming Language & Framework
- Java 17+
- Spring Boot

## How to Run

1. **Build and start the API:**

   ```sh
   ./mvnw spring-boot:run
   ```

   Or, if you have Maven installed:

   ```sh
   mvn spring-boot:run
   ```

2. The API will be available at `http://localhost:8080`.

## Endpoints

### Create User
- **POST** `/users`
- **Request Body:**
  ```json
  { "name": "John Doe", "email": "john@example.com" }
  ```
- **Response:**
  ```json
  { "id": "<uuid>", "name": "John Doe", "email": "john@example.com" }
  ```
- **Error (missing fields):**
  ```json
  { "error": "Missing required fields: name and email" }
  ```

#### Example curl:
```sh
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "John Doe", "email": "john@example.com"}'
```

### Get User by ID
- **GET** `/users/{id}`
- **Response:**
  ```json
  { "id": "<uuid>", "name": "John Doe", "email": "john@example.com" }
  ```
- **Error (not found):**
  ```json
  { "error": "User not found" }
  ```

#### Example curl:
```sh
curl http://localhost:8080/users/<uuid>
```

## Notes
- All data is stored in memory and will be lost when the server restarts.
- Only basic error handling is implemented as per requirements.
