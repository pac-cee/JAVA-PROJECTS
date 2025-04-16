package com.example.bank.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void testCustomerCreation() {
        Customer customer = new Customer(1, "John Doe");
        assertEquals(1, customer.getId());
        assertEquals("John Doe", customer.getName());
    }

    @Test
    void testToString() {
        Customer customer = new Customer(2, "Jane Doe");
        String str = customer.toString();
        assertTrue(str.contains("2"));
        assertTrue(str.contains("Jane Doe"));
    }
}
