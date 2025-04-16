package com.example.bank.model;

import com.example.bank.exception.InsufficientFundsException;

public class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 50.0;

    public SavingsAccount(int accountNumber, String ownerName, double initialBalance) {
        super(accountNumber, ownerName, initialBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdraw must be positive");
        if (balance - amount < MIN_BALANCE) throw new InsufficientFundsException("Insufficient funds. Savings must maintain minimum balance of " + MIN_BALANCE);
        balance -= amount;
    }
}
