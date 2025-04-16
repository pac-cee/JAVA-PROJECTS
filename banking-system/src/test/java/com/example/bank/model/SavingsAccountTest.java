package com.example.bank.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {
    @Test
    void testSavingsAccountCreation() {
        SavingsAccount account = new SavingsAccount(24680, "Bob", 2000.0);
        assertEquals(24680, account.getAccountNumber());
        assertEquals("Bob", account.getOwnerName());
        assertEquals(2000.0, account.getBalance());
    }

    @Test
    void testWithdrawWithinMinBalance() throws Exception {
        SavingsAccount account = new SavingsAccount(24680, "Bob", 200.0);
        account.withdraw(100.0); // Leaves 100, above min 50
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testWithdrawBelowMinBalance() {
        SavingsAccount account = new SavingsAccount(24680, "Bob", 100.0);
        assertThrows(com.example.bank.exception.InsufficientFundsException.class, () -> account.withdraw(60.0));
    }

    @Test
    void testWithdrawNegativeAmount() {
        SavingsAccount account = new SavingsAccount(24680, "Bob", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50.0));
    }
}
