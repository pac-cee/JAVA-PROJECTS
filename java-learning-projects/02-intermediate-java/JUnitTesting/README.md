# JUnitTesting Project

This project demonstrates how to write and run unit tests in Java using JUnit.

## Business Logic / Explanation

- **Unit Testing:**
  - The `CalculatorTest` class contains a simple `add` method and a unit test for it.
  - The `@Test` annotation marks the `testAdd` method as a test case.
  - `assertEquals` checks if the expected result matches the actual result.
- **Purpose:**
  - Unit tests help ensure your code is correct and make it easier to maintain and refactor.

## How to Run
1. Ensure you have JUnit in your project. For Maven, add this to your `pom.xml`:
   ```xml
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.13.2</version>
       <scope>test</scope>
   </dependency>
   ```
2. Compile the test:
   ```
   javac -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar CalculatorTest.java
   ```
3. Run the test:
   ```
   java -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore CalculatorTest
   ```

Expected output:
```
OK (1 test)
```

---

This example introduces automated testing, a critical skill for professional software development.
