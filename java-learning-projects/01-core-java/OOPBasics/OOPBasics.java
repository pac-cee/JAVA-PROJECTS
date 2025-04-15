// OOPBasics.java
// Demonstrates Object-Oriented Programming concepts: class, object, inheritance, and polymorphism.

// Base class (Parent)
class Animal {
    String name;
    Animal(String name) {
        this.name = name;
    }
    void speak() {
        System.out.println(name + " makes a sound.");
    }
}

// Derived class (Child) - Inheritance
class Dog extends Animal {
    Dog(String name) {
        super(name);
    }
    // Polymorphism: method overriding
    void speak() {
        System.out.println(name + " barks.");
    }
}

public class OOPBasics {
    public static void main(String[] args) {
        // Creating objects
        Animal genericAnimal = new Animal("GenericAnimal");
        Dog dog = new Dog("Buddy");

        // Demonstrate polymorphism
        genericAnimal.speak(); // Output: GenericAnimal makes a sound.
        dog.speak();           // Output: Buddy barks.
    }
}
