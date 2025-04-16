package com.example.bank.service;

import com.example.bank.model.*;
import com.example.bank.exception.InsufficientFundsException;
import java.util.*;

public class BankService {
    private Map<Integer, Account> accounts = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();
    private int accountSeq = 1000;

    public Account createAccount(String type, String ownerName, double initialBalance) {
        int accNum = ++accountSeq;
        Account acc;
        if ("Savings".equalsIgnoreCase(type)) {
            acc = new SavingsAccount(accNum, ownerName, initialBalance);
        } else {
            acc = new CheckingAccount(accNum, ownerName, initialBalance);
        }
        accounts.put(accNum, acc);
        transactions.add(new Transaction(accNum, "create", initialBalance, "Account created"));
        return acc;
    }

    public Account getAccount(int accNum) {
        return accounts.get(accNum);
    }

    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }

    public void deposit(int accNum, double amount) {
        Account acc = accounts.get(accNum);
        if (acc == null) throw new IllegalArgumentException("Account number " + accNum + " does not exist.");
        acc.deposit(amount);
        transactions.add(new Transaction(accNum, "deposit", amount, "Deposit"));
    }

    public void withdraw(int accNum, double amount) throws Exception {
        Account acc = accounts.get(accNum);
        if (acc == null) throw new IllegalArgumentException("Account number " + accNum + " does not exist.");
        acc.withdraw(amount);
        transactions.add(new Transaction(accNum, "withdraw", amount, "Withdraw"));
    }

    public void transfer(int fromAcc, int toAcc, double amount) throws Exception {
        Account src = accounts.get(fromAcc);
        Account dest = accounts.get(toAcc);
        if (src == null) throw new IllegalArgumentException("Source account " + fromAcc + " does not exist.");
        if (dest == null) throw new IllegalArgumentException("Destination account " + toAcc + " does not exist.");
        src.withdraw(amount);
        dest.deposit(amount);
        transactions.add(new Transaction(fromAcc, "transfer", amount, "Transfer to " + toAcc));
        transactions.add(new Transaction(toAcc, "transfer", amount, "Transfer from " + fromAcc));
    }

    public List<Transaction> getTransactions(int accNum) {
        if (!accounts.containsKey(accNum)) throw new IllegalArgumentException("Account number " + accNum + " does not exist.");
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAccountNumber() == accNum) result.add(t);
        }
        return result;
    }
}
