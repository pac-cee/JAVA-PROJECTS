// JDBCExample.java
// Demonstrates basic JDBC operations: connect, create table, insert, and query using SQLite.
import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:sample.db"; // SQLite DB file
        String createTable = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT);";
        String insertUser = "INSERT INTO users (name) VALUES ('Alice'), ('Bob');";
        String selectUsers = "SELECT * FROM users;";

        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            // Create table
            stmt.execute(createTable);
            // Insert data
            stmt.execute(insertUser);
            // Query data
            ResultSet rs = stmt.executeQuery(selectUsers);
            System.out.println("Users in database:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
