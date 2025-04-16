package com.example.bank.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void testAccountCreation() {
        CheckingAccount account = new CheckingAccount(12345, "John Doe", 1000.0);
        assertEquals(12345, account.getAccountNumber());
        assertEquals("John Doe", account.getOwnerName());
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    void testDeposit() {
        CheckingAccount account = new CheckingAccount(12345, "John Doe", 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void testDepositNegativeAmount() {
        CheckingAccount account = new CheckingAccount(12345, "John Doe", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10.0));
    }

    @Test
    void testToString() {
        CheckingAccount account = new CheckingAccount(12345, "John Doe", 100.0);
        String str = account.toString();
        assertTrue(str.contains("12345"));
        assertTrue(str.contains("John Doe"));
        assertTrue(str.contains("100.00"));
    }
}
