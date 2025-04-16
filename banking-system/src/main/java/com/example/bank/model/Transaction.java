package com.example.bank.model;

import java.time.LocalDateTime;

public class Transaction {
    private int accountNumber;
    private String type; // deposit, withdraw, transfer
    private double amount;
    private LocalDateTime timestamp;
    private String description;

    public Transaction(int accountNumber, String type, double amount, String description) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public int getAccountNumber() { return accountNumber; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f (%s)", timestamp, type, amount, description);
    }
}
