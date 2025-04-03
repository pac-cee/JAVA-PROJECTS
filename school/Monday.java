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
