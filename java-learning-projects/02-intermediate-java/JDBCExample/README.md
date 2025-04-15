# JDBCExample Project

This project demonstrates how to use JDBC in Java to connect to a database, create a table, insert data, and query data. SQLite is used for simplicity (no server setup required).

## Business Logic / Explanation

- **Database Connection:**
  - Uses SQLite and connects to a local file `sample.db`.
- **Table Creation:**
  - Creates a `users` table with `id` and `name` fields if it doesn't exist.
- **Insert Data:**
  - Inserts two users: Alice and Bob.
- **Query Data:**
  - Selects all users and prints their IDs and names.
- **Error Handling:**
  - All database operations are wrapped in try-catch blocks for safety.

## How to Run
1. Ensure you have the SQLite JDBC driver (`sqlite-jdbc-<version>.jar`). Download from: https://github.com/xerial/sqlite-jdbc
2. Place the driver JAR in your project directory.
3. Compile the program:
   ```
   javac -cp .;sqlite-jdbc-<version>.jar JDBCExample.java
   ```
4. Run the program:
   ```
   java -cp .;sqlite-jdbc-<version>.jar JDBCExample
   ```

Expected output:
```
Users in database:
1: Alice
2: Bob
```

---

This example is a foundation for backend data persistence in Java applications.
