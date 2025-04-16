package com.example.bank.model;

import com.example.bank.exception.InsufficientFundsException;

public class CheckingAccount extends Account {
    private static final double OVERDRAFT_LIMIT = -100.0;

    public CheckingAccount(int accountNumber, String ownerName, double initialBalance) {
        super(accountNumber, ownerName, initialBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdraw must be positive");
        if (balance - amount < OVERDRAFT_LIMIT) throw new InsufficientFundsException("Overdraft limit exceeded");
        balance -= amount;
    }
}
