package com.example.bank.model;

import java.io.Serializable;

public abstract class Account implements Serializable {
    protected int accountNumber;
    protected double balance;
    protected String ownerName;

    public Account(int accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public int getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getOwnerName() { return ownerName; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    public abstract void withdraw(double amount) throws Exception;

    @Override
    public String toString() {
        return String.format("%d - %s (%.2f)", accountNumber, ownerName, balance);
    }
}
