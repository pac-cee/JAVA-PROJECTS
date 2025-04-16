package com.example.bank.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    @Test
    void testTransactionCreation() {
        Transaction transaction = new Transaction(12345, "deposit", 100.0, "Initial deposit");
        assertEquals(12345, transaction.getAccountNumber());
        assertEquals("deposit", transaction.getType());
        assertEquals(100.0, transaction.getAmount());
        assertEquals("Initial deposit", transaction.getDescription());
        assertNotNull(transaction.getTimestamp());
    }

    @Test
    void testToString() {
        Transaction transaction = new Transaction(12345, "withdraw", 50.0, "ATM withdrawal");
        String str = transaction.toString();
        assertTrue(str.contains("withdraw"));
        assertTrue(str.contains("ATM withdrawal"));
        assertTrue(str.contains("50.00"));
    }
}
