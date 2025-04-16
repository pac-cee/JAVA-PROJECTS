package com.example.bank.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.example.bank.model.Transaction;
import com.example.bank.model.Account;

class BankServiceTest {
    @Test
    void testServiceInitialization() {
        // Example test: adjust based on BankService implementation
        BankService service = new BankService();
        assertNotNull(service);
    }

    @Test
    void testCreateAccount() {
        BankService bankService = new BankService();
        Account acc = bankService.createAccount("Checking", "John Doe", 500.0);
        assertNotNull(acc);
        assertEquals("John Doe", acc.getOwnerName());
        assertEquals(500.0, acc.getBalance());
    }

    @Test
    void testDeposit() {
        BankService bankService = new BankService();
        Account acc = bankService.createAccount("Savings", "Jane Doe", 100.0);
        bankService.deposit(acc.getAccountNumber(), 50.0);
        assertEquals(150.0, acc.getBalance());
    }

    @Test
    void testWithdraw() throws Exception {
        BankService bankService = new BankService();
        Account acc = bankService.createAccount("Checking", "John Doe", 200.0);
        bankService.withdraw(acc.getAccountNumber(), 100.0);
        assertEquals(100.0, acc.getBalance());
    }

    @Test
    void testTransfer() throws Exception {
        BankService bankService = new BankService();
        Account acc1 = bankService.createAccount("Checking", "A", 300.0);
        Account acc2 = bankService.createAccount("Savings", "B", 100.0);
        bankService.transfer(acc1.getAccountNumber(), acc2.getAccountNumber(), 50.0);
        assertEquals(250.0, acc1.getBalance());
        assertEquals(150.0, acc2.getBalance());
    }

    @Test
    void testGetTransactions() {
        BankService bankService = new BankService();
        Account acc = bankService.createAccount("Checking", "John Doe", 100.0);
        bankService.deposit(acc.getAccountNumber(), 50.0);
        bankService.deposit(acc.getAccountNumber(), 25.0);
        List<Transaction> txs = bankService.getTransactions(acc.getAccountNumber());
        assertEquals(3, txs.size()); // create + 2 deposits
    }
}
