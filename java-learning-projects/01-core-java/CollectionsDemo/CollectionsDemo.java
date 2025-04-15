// CollectionsDemo.java
// Demonstrates usage of Java Collections: List, Set, and Map.
import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        // List Example
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Apple"); // Allows duplicates
        System.out.println("List: " + fruits);

        // Set Example
        Set<String> uniqueFruits = new HashSet<>(fruits);
        System.out.println("Set: " + uniqueFruits); // No duplicates

        // Map Example
        Map<String, Integer> fruitCounts = new HashMap<>();
        for (String fruit : fruits) {
            fruitCounts.put(fruit, fruitCounts.getOrDefault(fruit, 0) + 1);
        }
        System.out.println("Map: " + fruitCounts); // Key-Value pairs
    }
}
