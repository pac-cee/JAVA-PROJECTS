# FileIOExample Project

This project demonstrates how to read from and write to a file in Java.

## Business Logic / Explanation

- **Writing to a File:**
  - Uses `BufferedWriter` and `FileWriter` to write the string `Hello, File IO!` to `example.txt`.
  - The `try-with-resources` statement ensures the file is closed automatically.
- **Reading from a File:**
  - Uses `BufferedReader` and `FileReader` to read the contents of `example.txt` line by line and print it to the console.
  - Handles exceptions for robust file operations.

## How to Run
1. Open a terminal in this directory.
2. Compile the program:
   ```
   javac FileIOExample.java
   ```
3. Run the program:
   ```
   java FileIOExample
   ```

Expected output:
```
Written to file: example.txt
Reading from file: example.txt
Hello, File IO!
```

---

This example introduces basic file input/output operations, which are essential for data persistence in applications.
