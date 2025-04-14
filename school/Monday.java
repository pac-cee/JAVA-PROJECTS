import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Custom exception for declaration errors
class DeclarationException extends Exception {
    public DeclarationException(String message) {
        super(message);
    }
}

// Abstract class that defines the structure for all declarations
abstract class Declaration {
    protected Taxpayer taxpayer;
    
    public Declaration(Taxpayer taxpayer) {
        this.taxpayer = taxpayer;
    }
    
    // Abstract methods to be implemented by subclasses
    public abstract double calculateVatTax() throws DeclarationException;
    public abstract double calculateIncomeTax() throws DeclarationException;
    public abstract void displayInfo();
}

// Taxpayer class with encapsulated fields and getters/setters
class Taxpayer {
    private int id;
    private String name;
    private double turnoverAmount;
    private double rate; // tax rate provided by the user (should match predefined rate)
    private String taxType; // \"VAT\" or \"Income Tax\"
    private int age;
    
    public Taxpayer(int id, String name, double turnoverAmount, double rate, String taxType, int age) {
        this.id = id;
        this.name = name;
        this.turnoverAmount = turnoverAmount;
        this.rate = rate;
        this.taxType = taxType;
        this.age = age;
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getTurnoverAmount() { return turnoverAmount; }
    public double getRate() { return rate; }
    public String getTaxType() { return taxType; }
    public int getAge() { return age; }
    
    @Override
    public String toString() {
        return "Taxpayer ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Turnover: " + turnoverAmount + "\n" +
               "Tax Rate: " + rate + "\n" +
               "Tax Type: " + taxType + "\n" +
               "Age: " + age;
    }
}

// VatDeclaration for VAT tax declarations (expected rate: 18% or 0.18)
class VatDeclaration extends Declaration {
    public VatDeclaration(Taxpayer taxpayer) {
        super(taxpayer);
    }
    
    @Override
    public double calculateVatTax() throws DeclarationException {
        // Validate that the taxpayer's rate and tax type are correct for VAT
        if (!taxpayer.getTaxType().equalsIgnoreCase("VAT") || taxpayer.getRate() != 0.18) {
            throw new DeclarationException("Invalid tax type or rate for VAT declaration.");
        }
        return taxpayer.getTurnoverAmount() * 0.18;
    }
    
    @Override
    public double calculateIncomeTax() {
        // Not applicable for VAT declaration
        return 0;
    }
    
    @Override
    public void displayInfo() {
        try {
            double tax = calculateVatTax();
            System.out.println("----- VAT Declaration -----");
            System.out.println(taxpayer);
            System.out.println(String.format("Calculated VAT (18%%): %.2f", tax));
            System.out.println("---------------------------");
        } catch (DeclarationException e) {
            System.out.println("Error displaying VAT Declaration: " + e.getMessage());
        }
    }
}

// IncomeTax declaration for income tax declarations (expected rate: 30% or 0.30)
class IncomeTax extends Declaration {
    public IncomeTax(Taxpayer taxpayer) {
        super(taxpayer);
    }
    
    @Override
    public double calculateVatTax() {
        // Not applicable for Income Tax declaration
        return 0;
    }
    
    @Override
    public double calculateIncomeTax() throws DeclarationException {
        // Validate that the taxpayer's rate and tax type are correct for Income Tax
        if (!taxpayer.getTaxType().equalsIgnoreCase("Income Tax") || taxpayer.getRate() != 0.30) {
            throw new DeclarationException("Invalid tax type or rate for Income Tax declaration.");
        }
        return taxpayer.getTurnoverAmount() * 0.30;
    }
    
    @Override
    public void displayInfo() {
        try {
            double tax = calculateIncomeTax();
            System.out.println("----- Income Tax Declaration -----");
            System.out.println(taxpayer);
            System.out.println(String.format("Calculated Income Tax (30%%): %.2f", tax));
            System.out.println("----------------------------------");
        } catch (DeclarationException e) {
            System.out.println("Error displaying Income Tax Declaration: " + e.getMessage());
        }
    }
}

// Payments class to process tax payments
class Payments extends Declaration {
    private double paymentAmount;
    
    public Payments(Taxpayer taxpayer, double paymentAmount) {
        super(taxpayer);
        this.paymentAmount = paymentAmount;
    }
    
    @Override
    public double calculateVatTax() throws DeclarationException {
        if (taxpayer.getTaxType().equalsIgnoreCase("VAT") && taxpayer.getRate() == 0.18) {
            return taxpayer.getTurnoverAmount() * 0.18;
        }
        return 0;
    }
    
    @Override
    public double calculateIncomeTax() throws DeclarationException {
        if (taxpayer.getTaxType().equalsIgnoreCase("Income Tax") && taxpayer.getRate() == 0.30) {
            return taxpayer.getTurnoverAmount() * 0.30;
        }
        return 0;
    }
    
    @Override
    public void displayInfo() {
        try {
            double expectedTax;
            if (taxpayer.getTaxType().equalsIgnoreCase("VAT")) {
                expectedTax = calculateVatTax();
            } else if (taxpayer.getTaxType().equalsIgnoreCase("Income Tax")) {
                expectedTax = calculateIncomeTax();
            } else {
                throw new DeclarationException("Unknown tax type.");
            }
            
            System.out.println("----- Payment Declaration -----");
            System.out.println(taxpayer);
            System.out.println(String.format("Expected Tax Payment: %.2f", expectedTax));
            System.out.println(String.format("Payment Made: %.2f", paymentAmount));
            if (Math.abs(paymentAmount - expectedTax) < 0.001) {
                System.out.println("Payment is valid and matches the expected tax.");
            } else {
                System.out.println("Payment does not match the expected tax amount!");
            }
            System.out.println("-------------------------------");
        } catch (DeclarationException e) {
            System.out.println("Error processing payment: " + e.getMessage());
        }
    }
}

// Main class: Tax Declaration System with a menu-driven terminal application.
public class TaxDeclarationSystem {
    // We'll store all declarations (VAT, Income Tax, Payments) in one list.
    private static List<Declaration> declarations = new ArrayList<>();
    
    public static void main(String[] args) {
        // Use try-with-resources for the Scanner.
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                printMenu();
                System.out.print("Select an option: ");
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1": // Declare Tax
                        try {
                            System.out.println("\n--- New Tax Declaration ---");
                            System.out.print("Enter Taxpayer ID (integer): ");
                            int id = Integer.parseInt(scanner.nextLine());
                            
                            System.out.print("Enter Full Name: ");
                            String name = scanner.nextLine();
                            
                            System.out.print("Enter Age (20-60): ");
                            int age = Integer.parseInt(scanner.nextLine());
                            if (age < 20 || age > 60) {
                                throw new DeclarationException("Tax declarations allowed only for ages 20 to 60.");
                            }
                            
                            System.out.print("Enter Turnover Amount: ");
                            double turnover = Double.parseDouble(scanner.nextLine());
                            
                            System.out.print("Enter Tax Type (VAT/Income Tax): ");
                            String taxType = scanner.nextLine().trim();
                            if (!taxType.equalsIgnoreCase("VAT") && !taxType.equalsIgnoreCase("Income Tax")) {
                                throw new DeclarationException("Tax Type must be either 'VAT' or 'Income Tax'.");
                            }
                            
                            System.out.print("Enter Tax Rate (e.g., 0.18 for VAT, 0.30 for Income Tax): ");
                            double rate = Double.parseDouble(scanner.nextLine());
                            // Validate tax rate based on tax type
                            if (taxType.equalsIgnoreCase("VAT") && rate != 0.18) {
                                throw new DeclarationException("VAT rate must be 0.18 (18%).");
                            }
                            if (taxType.equalsIgnoreCase("Income Tax") && rate != 0.30) {
                                throw new DeclarationException("Income Tax rate must be 0.30 (30%).");
                            }
                            
                            // Create the taxpayer object
                            Taxpayer taxpayer = new Taxpayer(id, name, turnover, rate, taxType, age);
                            
                            // Based on tax type, create the proper declaration and add to the list
                            if (taxType.equalsIgnoreCase("VAT")) {
                                Declaration declaration = new VatDeclaration(taxpayer);
                                declarations.add(declaration);
                                System.out.println("VAT Declaration recorded:");
                                declaration.displayInfo();
                            } else {
                                Declaration declaration = new IncomeTax(taxpayer);
                                declarations.add(declaration);
                                System.out.println("Income Tax Declaration recorded:");
                                declaration.displayInfo();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter numbers where required.");
                        } catch (DeclarationException e) {
                            System.out.println("Declaration Error: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Unexpected error: " + e.getMessage());
                        }
                        break;
                        
                    case "2": // Process Payment
                        try {
                            System.out.println("\n--- Process Tax Payment ---");
                            System.out.print("Enter Taxpayer ID for payment: ");
                            int payId = Integer.parseInt(scanner.nextLine());
                            
                            // Find the declaration for this taxpayer (assume unique id for simplicity)
                            Declaration target = null;
                            for (Declaration decl : declarations) {
                                if (decl.taxpayer.getId() == payId) {
                                    target = decl;
                                    break;
                                }
                            }
                            
                            if (target == null) {
                                System.out.println("No declaration found for Taxpayer ID " + payId);
                                break;
                            }
                            
                            System.out.print("Enter Payment Amount: ");
                            double payment = Double.parseDouble(scanner.nextLine());
                            
                            // Create a Payment declaration based on the found taxpayer
                            Declaration paymentDecl = new Payments(target.taxpayer, payment);
                            declarations.add(paymentDecl);
                            System.out.println("Payment processed:");
                            paymentDecl.displayInfo();
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter numbers where required.");
                        } catch (DeclarationException e) {
                            System.out.println("Payment Error: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Unexpected error: " + e.getMessage());
                        }
                        break;
                        
                    case "3": // List all declarations
                        System.out.println("\n--- All Tax Declarations ---");
                        if (declarations.isEmpty()) {
                            System.out.println("No declarations recorded yet.");
                        } else {
                            for (Declaration decl : declarations) {
                                decl.displayInfo();
                            }
                        }
                        break;
                        
                    case "4": // Exit
                        exit = true;
                        System.out.println("Exiting Tax Declaration System. Goodbye!");
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("System error: " + e.getMessage());
        }
    }
    
    // Method to print the menu options
    private static void printMenu() {
        System.out.println("\n--- Tax Declaration System Menu ---");
        System.out.println("1. Declare Tax");
        System.out.println("2. Process Payment");
        System.out.println("3. List All Declarations");
        System.out.println("4. Exit");
    }
}






/////////////////////////////////////////////////////////
////////////////////////////////////////////////////////


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Interface for all tax declarations
interface TaxDeclaration {
    double calculateTaxAmount();
    boolean validateDeclaration();
    void displayDeclarationInfo();
    Taxpayer getTaxpayer();
}

// Abstract class providing base functionality
abstract class Declaration implements TaxDeclaration {
    protected Taxpayer taxpayer;
    
    public Declaration(Taxpayer taxpayer) {
        this.taxpayer = taxpayer;
    }
    
    @Override
    public Taxpayer getTaxpayer() {
        return taxpayer;
    }
    
    @Override
    public boolean validateDeclaration() {
        // Common validation for all declarations
        if (taxpayer.getAge() < 20 || taxpayer.getAge() > 60) {
            System.out.println("Error: Taxpayer age must be between 20 and 60 years.");
            return false;
        }
        return true;
    }
    
    // Template method pattern
    public final void processDeclaration() {
        if (validateDeclaration()) {
            double taxAmount = calculateTaxAmount();
            System.out.println("Tax calculated: " + taxAmount);
            displayDeclarationInfo();
        } else {
            System.out.println("Declaration validation failed!");
        }
    }
}

// Encapsulated taxpayer class
class Taxpayer {
    private int id;
    private String name;
    private double turnoverAmount;
    private double rate;
    private String taxType;
    private int age;
    
    // Constructor
    public Taxpayer(int id, String name, double turnoverAmount, double rate, String taxType, int age) {
        this.id = id;
        this.name = name;
        this.turnoverAmount = turnoverAmount;
        this.rate = rate;
        this.taxType = taxType;
        this.age = age;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getTurnoverAmount() {
        return turnoverAmount;
    }
    
    public void setTurnoverAmount(double turnoverAmount) {
        this.turnoverAmount = turnoverAmount;
    }
    
    public double getRate() {
        return rate;
    }
    
    public void setRate(double rate) {
        this.rate = rate;
    }
    
    public String getTaxType() {
        return taxType;
    }
    
    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + 
               ", Name: " + name + 
               ", Age: " + age + 
               ", Tax Type: " + taxType + 
               ", Turnover: " + turnoverAmount;
    }
}

// Concrete implementation for VAT declarations
class VatDeclaration extends Declaration {
    private static final double VAT_RATE = 0.18; // 18%
    
    public VatDeclaration(Taxpayer taxpayer) {
        super(taxpayer);
    }
    
    @Override
    public double calculateTaxAmount() {
        return taxpayer.getTurnoverAmount() * VAT_RATE;
    }
    
    @Override
    public boolean validateDeclaration() {
        if (!super.validateDeclaration()) {
            return false;
        }
        
        if (!taxpayer.getTaxType().equalsIgnoreCase("VAT")) {
            System.out.println("Error: This declaration requires tax type VAT.");
            return false;
        }
        
        if (Math.abs(taxpayer.getRate() - VAT_RATE) > 0.001) {
            System.out.println("Error: VAT rate must be 18%.");
            return false;
        }
        
        return true;
    }
    
    @Override
    public void displayDeclarationInfo() {
        System.out.println("\n===== VAT DECLARATION =====");
        System.out.println(taxpayer.toString());
        System.out.println("VAT Rate: " + (VAT_RATE * 100) + "%");
        System.out.println("VAT Amount: " + calculateTaxAmount());
        System.out.println("===========================\n");
    }
}

// Concrete implementation for Income Tax declarations
class IncomeTaxDeclaration extends Declaration {
    private static final double INCOME_TAX_RATE = 0.30; // 30%
    
    public IncomeTaxDeclaration(Taxpayer taxpayer) {
        super(taxpayer);
    }
    
    @Override
    public double calculateTaxAmount() {
        return taxpayer.getTurnoverAmount() * INCOME_TAX_RATE;
    }
    
    @Override
    public boolean validateDeclaration() {
        if (!super.validateDeclaration()) {
            return false;
        }
        
        if (!taxpayer.getTaxType().equalsIgnoreCase("Income Tax")) {
            System.out.println("Error: This declaration requires tax type Income Tax.");
            return false;
        }
        
        if (Math.abs(taxpayer.getRate() - INCOME_TAX_RATE) > 0.001) {
            System.out.println("Error: Income Tax rate must be 30%.");
            return false;
        }
        
        return true;
    }
    
    @Override
    public void displayDeclarationInfo() {
        System.out.println("\n===== INCOME TAX DECLARATION =====");
        System.out.println(taxpayer.toString());
        System.out.println("Income Tax Rate: " + (INCOME_TAX_RATE * 100) + "%");
        System.out.println("Income Tax Amount: " + calculateTaxAmount());
        System.out.println("=================================\n");
    }
}

// Payment processing class
class Payment {
    private TaxDeclaration taxDeclaration;
    private double paymentAmount;
    
    public Payment(TaxDeclaration taxDeclaration, double paymentAmount) {
        this.taxDeclaration = taxDeclaration;
        this.paymentAmount = paymentAmount;
    }
    
    public boolean validatePayment() {
        double expectedAmount = taxDeclaration.calculateTaxAmount();
        if (Math.abs(paymentAmount - expectedAmount) > 0.01) {
            System.out.println("Error: Payment amount does not match tax amount.");
            System.out.println("Expected: " + expectedAmount + ", Received: " + paymentAmount);
            return false;
        }
        return true;
    }
    
    public void processPayment() {
        if (taxDeclaration.validateDeclaration() && validatePayment()) {
            System.out.println("\n===== PAYMENT PROCESSED =====");
            System.out.println("Taxpayer: " + taxDeclaration.getTaxpayer().getName());
            System.out.println("Tax Type: " + taxDeclaration.getTaxpayer().getTaxType());
            System.out.println("Amount Paid: " + paymentAmount);
            System.out.println("Payment Status: SUCCESSFUL");
            System.out.println("============================\n");
        } else {
            System.out.println("\n===== PAYMENT FAILED =====");
            System.out.println("Taxpayer: " + taxDeclaration.getTaxpayer().getName());
            System.out.println("Tax Type: " + taxDeclaration.getTaxpayer().getTaxType());
            System.out.println("Payment Status: REJECTED");
            System.out.println("=========================\n");
        }
    }
}

// Main application class
public class RRATaxSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<TaxDeclaration> declarations = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();
    
    public static void main(String[] args) {
        boolean running = true;
        
        try {
            while (running) {
                printMenu();
                int choice = getIntInput("Enter your choice: ");
                
                switch (choice) {
                    case 1:
                        addTaxpayer();
                        break;
                    case 2:
                        makePayment();
                        break;
                    case 3:
                        showAllDeclarations();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Thank you for using the RRA Tax System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    private static void printMenu() {
        System.out.println("\n=== RWANDA REVENUE AUTHORITY TAX SYSTEM ===");
        System.out.println("1. Register Tax Declaration");
        System.out.println("2. Make Payment");
        System.out.println("3. View All Declarations");
        System.out.println("0. Exit");
        System.out.println("==========================================");
    }
    
    private static void addTaxpayer() {
        try {
            System.out.println("\n=== REGISTER NEW TAX DECLARATION ===");
            
            int id = getIntInput("Enter Taxpayer ID: ");
            
            scanner.nextLine(); // Clear buffer
            System.out.print("Enter Taxpayer Name: ");
            String name = scanner.nextLine().trim();
            
            double turnover = getDoubleInput("Enter Turnover Amount: ");
            int age = getIntInput("Enter Taxpayer Age: ");
            
            String taxType = "";
            boolean validTaxType = false;
            while (!validTaxType) {
                System.out.print("Enter Tax Type (VAT or Income Tax): ");
                taxType = scanner.nextLine().trim();
                
                if (taxType.equalsIgnoreCase("VAT") || taxType.equalsIgnoreCase("Income Tax")) {
                    validTaxType = true;
                } else {
                    System.out.println("Error: Tax type must be VAT or Income Tax!");
                }
            }
            
            double rate;
            if (taxType.equalsIgnoreCase("VAT")) {
                rate = 0.18;
            } else {
                rate = 0.30;
            }
            
            // Create the taxpayer
            Taxpayer taxpayer = new Taxpayer(id, name, turnover, rate, taxType, age);
            
            // Create appropriate declaration
            TaxDeclaration declaration;
            if (taxType.equalsIgnoreCase("VAT")) {
                declaration = new VatDeclaration(taxpayer);
            } else {
                declaration = new IncomeTaxDeclaration(taxpayer);
            }
            
            // Process the declaration
            if (declaration.validateDeclaration()) {
                declarations.add(declaration);
                System.out.println("\nDeclaration registered successfully!");
                declaration.displayDeclarationInfo();
            }
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format!");
            scanner.nextLine(); // Clear buffer
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void makePayment() {
        if (declarations.isEmpty()) {
            System.out.println("No declarations available. Please register a declaration first.");
            return;
        }
        
        try {
            System.out.println("\n=== MAKE PAYMENT ===");
            System.out.println("Available declarations:");
            
            for (int i = 0; i < declarations.size(); i++) {
                TaxDeclaration declaration = declarations.get(i);
                System.out.println((i + 1) + ". " + declaration.getTaxpayer().getName() + 
                                  " - " + declaration.getTaxpayer().getTaxType() + 
                                  " - Amount due: " + declaration.calculateTaxAmount());
            }
            
            int index = getIntInput("Select declaration (1-" + declarations.size() + "): ") - 1;
            
            if (index < 0 || index >= declarations.size()) {
                System.out.println("Invalid selection!");
                return;
            }
            
            TaxDeclaration selectedDeclaration = declarations.get(index);
            double amountDue = selectedDeclaration.calculateTaxAmount();
            
            System.out.println("Amount due: " + amountDue);
            double paymentAmount = getDoubleInput("Enter payment amount: ");
            
            Payment payment = new Payment(selectedDeclaration, paymentAmount);
            payment.processPayment();
            payments.add(payment);
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format!");
            scanner.nextLine(); // Clear buffer
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void showAllDeclarations() {
        if (declarations.isEmpty()) {
            System.out.println("No declarations registered yet.");
            return;
        }
        
        System.out.println("\n=== ALL REGISTERED DECLARATIONS ===");
        for (TaxDeclaration declaration : declarations) {
            declaration.displayDeclarationInfo();
        }
    }
    
    private static int getIntInput(String prompt) {
        int value = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number!");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        
        scanner.nextLine(); // Clear buffer
        return value;
    }
    
    private static double getDoubleInput(String prompt) {
        double value = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                System.out.print(prompt);
                value = scanner.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number!");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        
        scanner.nextLine(); // Clear buffer
        return value;
    }
}






////////////////////////////////////////////
//////////////////////////////////////////





import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class TaxDeclarationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Declaration> declarations = new ArrayList<>();
        
        System.out.println("===== Rwanda Revenue Authority Tax Declaration System =====");
        
        boolean continueProgram = true;
        while (continueProgram) {
            try {
                System.out.println("\nSelect an option:");
                System.out.println("1. Create VAT Declaration");
                System.out.println("2. Create Income Tax Declaration");
                System.out.println("3. Process Payment");
                System.out.println("4. Display All Declarations");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        declarations.add(createVatDeclaration(scanner));
                        break;
                    case 2:
                        declarations.add(createIncomeTaxDeclaration(scanner));
                        break;
                    case 3:
                        declarations.add(processPayment(scanner));
                        break;
                    case 4:
                        displayAllDeclarations(declarations);
                        break;
                    case 5:
                        continueProgram = false;
                        System.out.println("Thank you for using RRA Tax Declaration System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    public static VatDeclaration createVatDeclaration(Scanner scanner) {
        try {
            Taxpayer taxpayer = createTaxpayer(scanner, "VAT");
            return new VatDeclaration(taxpayer);
        } catch (Exception e) {
            System.out.println("Error creating VAT declaration: " + e.getMessage());
            throw e;
        }
    }
    
    public static IncomeTax createIncomeTaxDeclaration(Scanner scanner) {
        try {
            Taxpayer taxpayer = createTaxpayer(scanner, "Income Tax");
            return new IncomeTax(taxpayer);
        } catch (Exception e) {
            System.out.println("Error creating Income Tax declaration: " + e.getMessage());
            throw e;
        }
    }
    
    public static Payments processPayment(Scanner scanner) {
        try {
            System.out.println("\n--- Process Payment ---");
            System.out.print("Enter taxpayer ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter taxpayer name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter taxpayer age: ");
            int age = scanner.nextInt();
            validateAge(age);
            
            System.out.print("Enter tax type (VAT/Income Tax): ");
            scanner.nextLine(); // Consume newline
            String taxType = scanner.nextLine();
            validateTaxType(taxType);
            
            System.out.print("Enter turnover amount: ");
            double turnoverAmount = scanner.nextDouble();
            validateAmount(turnoverAmount);
            
            System.out.print("Enter payment amount: ");
            double paymentAmount = scanner.nextDouble();
            validateAmount(paymentAmount);
            
            Taxpayer taxpayer = new Taxpayer(id, name, turnoverAmount, taxType, age);
            return new Payments(taxpayer, paymentAmount);
        } catch (Exception e) {
            System.out.println("Error processing payment: " + e.getMessage());
            throw e;
        }
    }
    
    public static Taxpayer createTaxpayer(Scanner scanner, String taxType) {
        System.out.println("\n--- Create " + taxType + " Declaration ---");
        
        System.out.print("Enter taxpayer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter taxpayer name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter taxpayer age: ");
        int age = scanner.nextInt();
        validateAge(age);
        
        System.out.print("Enter turnover amount: ");
        double turnoverAmount = scanner.nextDouble();
        validateAmount(turnoverAmount);
        
        return new Taxpayer(id, name, turnoverAmount, taxType, age);
    }
    
    public static void displayAllDeclarations(ArrayList<Declaration> declarations) {
        if (declarations.isEmpty()) {
            System.out.println("\nNo declarations have been created yet.");
            return;
        }
        
        System.out.println("\n===== All Declarations =====");
        for (Declaration declaration : declarations) {
            declaration.displayInfo();
        }
    }
    
    // Validation methods
    public static void validateAge(int age) {
        if (age < 20 || age > 60) {
            throw new IllegalArgumentException("Age must be between 20 and 60 years");
        }
    }
    
    public static void validateTaxType(String taxType) {
        if (!taxType.equalsIgnoreCase("VAT") && !taxType.equalsIgnoreCase("Income Tax")) {
            throw new IllegalArgumentException("Tax type must be either 'VAT' or 'Income Tax'");
        }
    }
    
    public static void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }
}

// Abstract class Declaration
abstract class Declaration {
    public abstract double calculateVatTax();
    public abstract double calculateIncomeTax();
    public abstract void displayInfo();
}

// Taxpayer class with encapsulation
class Taxpayer {
    private int id;
    private String name;
    private double turnoverAmount;
    private double rate;
    private String taxType;
    private int age;
    
    public Taxpayer(int id, String name, double turnoverAmount, String taxType, int age) {
        this.id = id;
        this.name = name;
        this.turnoverAmount = turnoverAmount;
        this.taxType = taxType;
        
        // Validate age
        if (age < 20 || age > 60) {
            throw new IllegalArgumentException("Age must be between 20 and 60 years");
        }
        this.age = age;
        
        // Set rate based on tax type
        this.setRate(taxType);
    }
    
    // Getters and setters with validation
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getTurnoverAmount() {
        return turnoverAmount;
    }
    
    public void setTurnoverAmount(double turnoverAmount) {
        if (turnoverAmount <= 0) {
            throw new IllegalArgumentException("Turnover amount must be greater than 0");
        }
        this.turnoverAmount = turnoverAmount;
    }
    
    public double getRate() {
        return rate;
    }
    
    public void setRate(String taxType) {
        if (taxType.equalsIgnoreCase("VAT")) {
            this.rate = 0.18; // 18% for VAT
        } else if (taxType.equalsIgnoreCase("Income Tax")) {
            this.rate = 0.30; // 30% for Income Tax
        } else {
            throw new IllegalArgumentException("Invalid tax type. Must be 'VAT' or 'Income Tax'");
        }
    }
    
    public String getTaxType() {
        return taxType;
    }
    
    public void setTaxType(String taxType) {
        if (!taxType.equalsIgnoreCase("VAT") && !taxType.equalsIgnoreCase("Income Tax")) {
            throw new IllegalArgumentException("Tax type must be either 'VAT' or 'Income Tax'");
        }
        this.taxType = taxType;
        this.setRate(taxType); // Update rate when tax type changes
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        if (age < 20 || age > 60) {
            throw new IllegalArgumentException("Age must be between 20 and 60 years");
        }
        this.age = age;
    }
}

// VAT Declaration class
class VatDeclaration extends Declaration {
    private Taxpayer taxpayer;
    
    public VatDeclaration(Taxpayer taxpayer) {
        // Validate tax type
        if (!taxpayer.getTaxType().equalsIgnoreCase("VAT")) {
            throw new IllegalArgumentException("Tax type must be VAT for VAT declaration");
        }
        this.taxpayer = taxpayer;
    }
    
    @Override
    public double calculateVatTax() {
        return taxpayer.getTurnoverAmount() * taxpayer.getRate();
    }
    
    @Override
    public double calculateIncomeTax() {
        throw new UnsupportedOperationException("Income tax calculation not supported in VAT declaration");
    }
    
    @Override
    public void displayInfo() {
        System.out.println("\n========== VAT Declaration ==========");
        System.out.println("Taxpayer ID: " + taxpayer.getId());
        System.out.println("Name: " + taxpayer.getName());
        System.out.println("Age: " + taxpayer.getAge());
        System.out.println("Turnover Amount: " + taxpayer.getTurnoverAmount());
        System.out.println("VAT Rate: " + (taxpayer.getRate() * 100) + "%");
        System.out.println("VAT Amount: " + calculateVatTax());
        System.out.println("=====================================");
    }
}

// Income Tax class
class IncomeTax extends Declaration {
    private Taxpayer taxpayer;
    
    public IncomeTax(Taxpayer taxpayer) {
        // Validate tax type
        if (!taxpayer.getTaxType().equalsIgnoreCase("Income Tax")) {
            throw new IllegalArgumentException("Tax type must be Income Tax for Income Tax declaration");
        }
        this.taxpayer = taxpayer;
    }
    
    @Override
    public double calculateVatTax() {
        throw new UnsupportedOperationException("VAT calculation not supported in Income Tax declaration");
    }
    
    @Override
    public double calculateIncomeTax() {
        return taxpayer.getTurnoverAmount() * taxpayer.getRate();
    }
    
    @Override
    public void displayInfo() {
        System.out.println("\n========== Income Tax Declaration ==========");
        System.out.println("Taxpayer ID: " + taxpayer.getId());
        System.out.println("Name: " + taxpayer.getName());
        System.out.println("Age: " + taxpayer.getAge());
        System.out.println("Turnover Amount: " + taxpayer.getTurnoverAmount());
        System.out.println("Income Tax Rate: " + (taxpayer.getRate() * 100) + "%");
        System.out.println("Income Tax Amount: " + calculateIncomeTax());
        System.out.println("==========================================");
    }
}

// Payments class
class Payments extends Declaration {
    private Taxpayer taxpayer;
    private double amountPaid;
    private double taxDue;
    
    public Payments(Taxpayer taxpayer, double amountPaid) {
        this.taxpayer = taxpayer;
        this.amountPaid = amountPaid;
        
        // Calculate tax due based on tax type
        if (taxpayer.getTaxType().equalsIgnoreCase("VAT")) {
            this.taxDue = taxpayer.getTurnoverAmount() * 0.18;
        } else if (taxpayer.getTaxType().equalsIgnoreCase("Income Tax")) {
            this.taxDue = taxpayer.getTurnoverAmount() * 0.30;
        } else {
            throw new IllegalArgumentException("Invalid tax type");
        }
    }
    
    public boolean isPaymentComplete() {
        return amountPaid >= taxDue;
    }
    
    @Override
    public double calculateVatTax() {
        if (taxpayer.getTaxType().equalsIgnoreCase("VAT")) {
            return taxDue;
        }
        throw new UnsupportedOperationException("Not a VAT declaration");
    }
    
    @Override
    public double calculateIncomeTax() {
        if (taxpayer.getTaxType().equalsIgnoreCase("Income Tax")) {
            return taxDue;
        }
        throw new UnsupportedOperationException("Not an Income Tax declaration");
    }
    
    @Override
    public void displayInfo() {
        System.out.println("\n========== Payment Information ==========");
        System.out.println("Taxpayer ID: " + taxpayer.getId());
        System.out.println("Name: " + taxpayer.getName());
        System.out.println("Tax Type: " + taxpayer.getTaxType());
        System.out.println("Tax Due: " + taxDue);
        System.out.println("Amount Paid: " + amountPaid);
        
        if (isPaymentComplete()) {
            System.out.println("Payment Status: Complete");
            if (amountPaid > taxDue) {
                System.out.println("Refund Amount: " + (amountPaid - taxDue));
            }
        } else {
            System.out.println("Payment Status: Incomplete");
            System.out.println("Remaining Balance: " + (taxDue - amountPaid));
        }
        System.out.println("========================================");
    }
}