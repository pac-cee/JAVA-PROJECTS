Let's create a simple banking application with clean code practices. We'll use a real-world example of a banking system where users can check balances, deposit, withdraw, and view transactions.

### Step 1: Create Domain Classes
First, define core business objects.

**1. BankAccount.java**  
Represents a bank account and handles financial operations.

```java
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final String accountNumber;
    private final String accountHolder;
    private BigDecimal balance;
    private final List<Transaction> transactions;

    public BankAccount(String accountNumber, String accountHolder, BigDecimal initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public void deposit(BigDecimal amount) {
        validatePositiveAmount(amount);
        balance = balance.add(amount);
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount, LocalDateTime.now()));
    }

    public void withdraw(BigDecimal amount) {
        validatePositiveAmount(amount);
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance = balance.subtract(amount);
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount, LocalDateTime.now()));
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactions);
    }

    private void validatePositiveAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
```

**2. Transaction.java**  
Immutable transaction record.

```java
import java.time.LocalDateTime;

public record Transaction(TransactionType type, BigDecimal amount, LocalDateTime timestamp) {
    @Override
    public String toString() {
        return String.format("[%s] %-10s %12s",
                timestamp.toLocalDate(),
                type,
                "$" + amount.setScale(2));
    }
}
```

**3. TransactionType.java**  
Enum for transaction types.

```java
public enum TransactionType {
    DEPOSIT,
    WITHDRAWAL
}
```

### Step 2: Create Input Validation
**4. InputValidator.java**  
Handles user input validation.

```java
import java.math.BigDecimal;

public class InputValidator {
    public static BigDecimal validateAmount(String input) {
        try {
            BigDecimal amount = new BigDecimal(input);
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero");
            }
            return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format");
        }
    }
}
```

### Step 3: Build the Main Application
**5. BankingApp.java**  
Main class with user interface.

```java
import java.math.BigDecimal;
import java.util.Scanner;

public class BankingApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static BankAccount account;

    public static void main(String[] args) {
        initializeAccount();
        displayMainMenu();
    }

    private static void initializeAccount() {
        account = new BankAccount("ACC-123456", "John Doe", new BigDecimal("1000.00"));
    }

    private static void displayMainMenu() {
        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1" -> checkBalance();
                case "2" -> depositMoney();
                case "3" -> withdrawMoney();
                case "4" -> viewTransactions();
                case "5" -> {
                    System.out.println("Thank you for banking with us!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void checkBalance() {
        System.out.printf("Current Balance: $%.2f%n", account.getBalance());
    }

    private static void depositMoney() {
        System.out.print("Enter deposit amount: ");
        processFinancialOperation(OperationType.DEPOSIT);
    }

    private static void withdrawMoney() {
        System.out.print("Enter withdrawal amount: ");
        processFinancialOperation(OperationType.WITHDRAWAL);
    }

    private static void processFinancialOperation(OperationType operation) {
        try {
            BigDecimal amount = InputValidator.validateAmount(scanner.nextLine());
            switch (operation) {
                case DEPOSIT -> account.deposit(amount);
                case WITHDRAWAL -> account.withdraw(amount);
            }
            System.out.printf("%s successful. New balance: $%.2f%n",
                    operation, account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Operation failed: " + e.getMessage());
        }
    }

    private static void viewTransactions() {
        System.out.println("\nTransaction History:");
        account.getTransactionHistory().forEach(System.out::println);
    }

    private enum OperationType {
        DEPOSIT, WITHDRAWAL
    }
}
```

### Key Clean Code Practices Used:
1. **Single Responsibility Principle**  
   - Each class handles a specific responsibility (BankAccount for account operations, Transaction for data storage)
   
2. **Immutability**  
   - Transaction record is immutable
   
3. **Validation Separation**  
   - Input validation logic separated into its own class
   
4. **Defensive Programming**  
   - BankAccount validates amounts before operations
   
5. **DRY Principle**  
   - Financial operations consolidated into `processFinancialOperation` method

6. **Type Safety**  
   - Uses BigDecimal for monetary values to avoid floating-point errors

### How to Run:
1. Create all Java files in the same directory
2. Compile: `javac *.java`
3. Run: `java BankingApp`

### Example Usage:
```text
=== Banking System ===
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. View Transactions
5. Exit
Enter your choice: 2
Enter deposit amount: 500
Deposit successful. New balance: $1500.00

=== Banking System ===
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. View Transactions
5. Exit
Enter your choice: 4

Transaction History:
[2023-08-04] DEPOSIT          $500.00
```

This implementation provides a clean, maintainable foundation that can be extended with additional features like multiple accounts, persistence, or more complex transaction types.

Here's a comprehensive approach to exception handling in Java OOP using a real-world hotel booking system example. We'll implement layered exception handling with custom exceptions, input validation, and graceful recovery:

### 1. Exception Hierarchy Design
```java
// Base exception
public class HotelException extends RuntimeException {
    public HotelException(String message) {
        super(message);
    }
}

// Specific exceptions
public class InvalidInputException extends HotelException {
    public InvalidInputException(String message) {
        super("Invalid input: " + message);
    }
}

public class RoomUnavailableException extends HotelException {
    public RoomUnavailableException(int roomNumber) {
        super("Room " + roomNumber + " is unavailable");
    }
}

public class PaymentFailedException extends HotelException {
    public PaymentFailedException(String message) {
        super("Payment failed: " + message);
    }
}

public class DatabaseException extends HotelException {
    public DatabaseException(String operation) {
        super("Database operation failed: " + operation);
    }
}
```

### 2. Input Handling with Validation
```java
public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                
                if (value < min || value > max) {
                    throw new InvalidInputException(
                        "Value must be between " + min + " and " + max
                    );
                }
                return value;
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format. Please try again.");
            } catch (HotelException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static LocalDate getFutureDate(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " (YYYY-MM-DD): ");
                String input = scanner.nextLine().trim();
                LocalDate date = LocalDate.parse(input);
                
                if (date.isBefore(LocalDate.now())) {
                    throw new InvalidInputException("Date must be in the future");
                }
                return date;
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date format. Please try again.");
            } catch (HotelException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
```

### 3. Main Application with Error Recovery
```java
public class HotelBookingSystem {
    private static final BookingService bookingService = new BookingService();
    
    public static void main(String[] args) {
        try {
            runApplication();
        } catch (DatabaseException e) {
            System.err.println("Critical database error: " + e.getMessage());
            System.err.println("Please contact system administrator");
            System.exit(1);
        } finally {
            System.out.println("Thank you for using our booking system");
        }
    }

    private static void runApplication() {
        while (true) {
            try {
                displayMainMenu();
                int choice = InputHandler.getIntInput("Enter choice: ", 1, 5);
                
                if (choice == 5) break;
                
                handleMenuChoice(choice);
            } catch (HotelException e) {
                logError(e);
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> bookRoom();
            case 2 -> cancelBooking();
            case 3 -> viewBookings();
            case 4 -> processPayment();
            default -> throw new InvalidInputException("Invalid menu option");
        }
    }

    private static void bookRoom() {
        int roomNumber = InputHandler.getIntInput("Enter room number: ", 100, 999);
        LocalDate checkIn = InputHandler.getFutureDate("Check-in date");
        LocalDate checkOut = InputHandler.getFutureDate("Check-out date");
        
        try {
            Booking booking = bookingService.createBooking(
                roomNumber, 
                checkIn, 
                checkOut
            );
            System.out.println("Booking created: " + booking);
        } catch (RoomUnavailableException e) {
            System.err.println(e.getMessage());
            suggestAlternativeRooms(roomNumber);
        }
    }

    private static void processPayment() {
        try {
            PaymentResult result = bookingService.processPayment();
            if (!result.isSuccess()) {
                throw new PaymentFailedException(result.getErrorMessage());
            }
            System.out.println("Payment processed successfully");
        } catch (PaymentFailedException e) {
            System.err.println(e.getMessage());
            offerPaymentRetry();
        }
    }

    private static void logError(Exception e) {
        // Implement proper error logging
        System.err.println("[" + LocalDateTime.now() + "] ERROR: " + e.getMessage());
    }
}
```

### Best Practices Demonstrated:

1. **Layered Exception Handling**
   - Custom exception hierarchy for domain-specific errors
   - Built-in exceptions for technical errors (NumberFormatException, etc.)
   - Separation of business logic exceptions from technical exceptions

2. **Graceful Recovery**
   - Input validation loops with informative messages
   - Alternative suggestions on room unavailability
   - Payment retry mechanism
   - Safe menu navigation with input constraints

3. **Error Context Preservation**
   ```java
   // Example of wrapping exceptions
   try {
       database.saveBooking(booking);
   } catch (SQLException e) {
       throw new DatabaseException("Save booking", e);
   }
   ```

4. **Resource Management**
   ```java
   // Example using try-with-resources
   public List<Booking> loadBookings() {
       try (InputStream is = Files.newInputStream(bookingsFile)) {
           // Read bookings
       } catch (IOException e) {
           throw new DatabaseException("Load bookings", e);
       }
   }
   ```

5. **Error Reporting**
   - User-friendly messages
   - Technical logging (with date/time stamps)
   - Separate error messages for users vs developers

6. **Input Validation Strategy**
   - Centralized input handling
   - Range validation
   - Date validation
   - Type validation
   - Custom validation rules

### Key Principles:
1. **Fail Fast**: Validate inputs immediately
2. **Fail Clearly**: Provide specific error messages
3. **Recover Gracefully**: Always return to stable state
4. **Separate Concerns**: Different handling for business vs technical errors
5. **Log Thoroughly**: Preserve debugging information
6. **Prevent Cascading Failures**: Handle errors at appropriate layers

This structure allows for:
- Adding new exception types without breaking existing code
- Localized error handling where recovery is possible
- Centralized handling of critical errors
- Clean separation between normal flow and error handling
- Easy maintenance through consistent error handling patterns

To handle multiple exceptions intelligently:
1. Catch specific exceptions first
2. Handle recoverable errors at the lowest possible level
3. Wrap technical exceptions in domain-specific exceptions
4. Use finally blocks for cleanup operations
5. Maintain error state awareness in long-running operations