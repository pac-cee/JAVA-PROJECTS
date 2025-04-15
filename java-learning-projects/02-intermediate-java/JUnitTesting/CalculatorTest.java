// CalculatorTest.java
// Demonstrates unit testing in Java with JUnit.
// Note: Requires JUnit dependency in your project.
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    // Simple method to test
    public int add(int a, int b) {
        return a + b;
    }

    @Test
    public void testAdd() {
        assertEquals(5, add(2, 3));
        assertEquals(0, add(-2, 2));
        assertEquals(-5, add(-2, -3));
    }
}
