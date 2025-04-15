// FileIOExample.java
// Demonstrates reading from and writing to a file in Java.
import java.io.*;

public class FileIOExample {
    public static void main(String[] args) {
        String filename = "example.txt";
        String content = "Hello, File IO!";

        // Writing to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("Written to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        // Reading from a file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Reading from file: " + filename);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
