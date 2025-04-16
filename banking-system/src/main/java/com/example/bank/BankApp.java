package com.example.bank;

import com.example.bank.model.Account;
import com.example.bank.service.BankService;
import com.example.bank.exception.InsufficientFundsException;

import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class BankApp extends JFrame {
    private BankService bankService = new BankService();
    private JTextArea outputArea = new JTextArea(15, 40);

    public BankApp() {
        setTitle("Java Banking System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton btnCreate = new JButton("Create Account");
        JButton btnDeposit = new JButton("Deposit");
        JButton btnWithdraw = new JButton("Withdraw");
        JButton btnTransfer = new JButton("Transfer");
        JButton btnShowAccounts = new JButton("Show Accounts");
        panel.add(btnCreate);
        panel.add(btnDeposit);
        panel.add(btnWithdraw);
        panel.add(btnTransfer);
        panel.add(btnShowAccounts);
        add(panel, BorderLayout.SOUTH);

        btnCreate.addActionListener(this::createAccount);
        btnDeposit.addActionListener(this::deposit);
        btnWithdraw.addActionListener(this::withdraw);
        btnTransfer.addActionListener(this::transfer);
        btnShowAccounts.addActionListener(e -> showAccounts());

        // Disable transfer if less than 2 accounts
        btnTransfer.setEnabled(bankService.getAllAccounts().size() > 1);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createAccount(ActionEvent e) {
        String[] types = {"Savings", "Checking"};
        String type = (String) JOptionPane.showInputDialog(this, "Account Type", "Create Account", JOptionPane.PLAIN_MESSAGE, null, types, types[0]);
        if (type == null) return;
        String name = JOptionPane.showInputDialog(this, "Owner Name:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Owner name is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String balStr = JOptionPane.showInputDialog(this, "Initial Balance:");
        if (balStr == null) return;
        try {
            double balance = Double.parseDouble(balStr);
            if (balance < 0) throw new NumberFormatException();
            Account acc = bankService.createAccount(type, name, balance);
            outputArea.append("Created: " + acc + "\n");
            JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Initial balance must be a non-negative number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deposit(ActionEvent e) {
        String accStr = JOptionPane.showInputDialog(this, "Account Number:");
        if (accStr == null) return;
        String amtStr = JOptionPane.showInputDialog(this, "Amount to Deposit:");
        if (amtStr == null) return;
        try {
            int accNum = Integer.parseInt(accStr);
            double amt = Double.parseDouble(amtStr);
            if (amt <= 0) throw new NumberFormatException();
            bankService.deposit(accNum, amt);
            outputArea.append("Deposited " + amt + " to " + accNum + "\n");
            JOptionPane.showMessageDialog(this, "Deposit successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please enter valid, positive numbers for account and amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Account Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void withdraw(ActionEvent e) {
        String accStr = JOptionPane.showInputDialog(this, "Account Number:");
        if (accStr == null) return;
        String amtStr = JOptionPane.showInputDialog(this, "Amount to Withdraw:");
        if (amtStr == null) return;
        try {
            int accNum = Integer.parseInt(accStr);
            double amt = Double.parseDouble(amtStr);
            if (amt <= 0) throw new NumberFormatException();
            bankService.withdraw(accNum, amt);
            outputArea.append("Withdrew " + amt + " from " + accNum + "\n");
            JOptionPane.showMessageDialog(this, "Withdrawal successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please enter valid, positive numbers for account and amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Account Error", JOptionPane.ERROR_MESSAGE);
        } catch (InsufficientFundsException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void transfer(ActionEvent e) {
        if (bankService.getAllAccounts().size() < 2) {
            JOptionPane.showMessageDialog(this, "At least two accounts are required to transfer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String fromStr = JOptionPane.showInputDialog(this, "From Account Number:");
        if (fromStr == null) return;
        String toStr = JOptionPane.showInputDialog(this, "To Account Number:");
        if (toStr == null) return;
        String amtStr = JOptionPane.showInputDialog(this, "Amount to Transfer:");
        if (amtStr == null) return;
        try {
            int from = Integer.parseInt(fromStr);
            int to = Integer.parseInt(toStr);
            double amt = Double.parseDouble(amtStr);
            if (amt <= 0) throw new NumberFormatException();
            if (from == to) throw new IllegalArgumentException("Cannot transfer to the same account.");
            bankService.transfer(from, to, amt);
            outputArea.append("Transferred " + amt + " from " + from + " to " + to + "\n");
            JOptionPane.showMessageDialog(this, "Transfer successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please enter valid, positive numbers for accounts and amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Account Error", JOptionPane.ERROR_MESSAGE);
        } catch (InsufficientFundsException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAccounts() {
        Collection<Account> accs = bankService.getAllAccounts();
        outputArea.append("Accounts:\n");
        for (Account a : accs) {
            outputArea.append(a.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
        SwingUtilities.invokeLater(BankApp::new);
    }
}
