# OOPBasics Project

This project demonstrates the core concepts of Object-Oriented Programming (OOP) in Java:

- **Class & Object**: How to define classes and create objects.
- **Inheritance**: How a subclass (Dog) inherits from a superclass (Animal).
- **Polymorphism**: How a subclass can override methods of its superclass.

## Business Logic / Explanation

- `Animal` is a base class with a `name` property and a `speak()` method.
- `Dog` extends `Animal` and overrides the `speak()` method to provide a dog-specific implementation.
- In `main`, we create an `Animal` and a `Dog` object and call their `speak()` methods to demonstrate both inheritance and polymorphism.

## How to Run
1. Open a terminal in this directory.
2. Compile the program:
   ```
   javac OOPBasics.java
   ```
3. Run the program:
   ```
   java OOPBasics
   ```

Expected output:
```
GenericAnimal makes a sound.
Buddy barks.
```

---

This example forms the foundation for more advanced OOP concepts in Java.
