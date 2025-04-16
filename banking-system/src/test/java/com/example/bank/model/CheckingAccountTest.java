package com.example.bank.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {
    @Test
    void testCheckingAccountCreation() {
        CheckingAccount account = new CheckingAccount(67890, "Alice", 500.0);
        assertEquals(67890, account.getAccountNumber());
        assertEquals("Alice", account.getOwnerName());
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void testWithdrawWithinLimit() throws Exception {
        CheckingAccount account = new CheckingAccount(67890, "Alice", 100.0);
        account.withdraw(150.0); // Should allow overdraft up to -100
        assertEquals(-50.0, account.getBalance());
    }

    @Test
    void testWithdrawExceedOverdraft() {
        CheckingAccount account = new CheckingAccount(67890, "Alice", 0.0);
        assertThrows(com.example.bank.exception.InsufficientFundsException.class, () -> account.withdraw(200.0));
    }

    @Test
    void testWithdrawNegativeAmount() {
        CheckingAccount account = new CheckingAccount(67890, "Alice", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50.0));
    }
}
