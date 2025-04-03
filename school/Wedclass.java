import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Custom exception for booking errors
class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}

// Interface defining three methods for Passenger Management
interface PassengerManagement {
    // Books a ticket for a passenger based on id, seat, destination and travel distance.
    void bookTicket(String passengerId, int seatNumber, String destination, double distance) throws BookingException;
    // Calculates fare based on distance and whether discount applies.
    double calculateFare(double distance, boolean isDiscounted);
    // Tracks a bus given its bus number.
    void trackBus(String busNumber);
}

// Passenger class with encapsulated fields and appropriate getters.
class Passenger {
    private String id;
    private String name;
    private int age;
    private int ticketNumber;
    private int seatNumber;
    private String destination;
    private double distance;
    private String plateNumber;

    public Passenger(String id, String name, int age, int ticketNumber,
                     int seatNumber, String destination, double distance, String plateNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ticketNumber = ticketNumber;
        this.seatNumber = seatNumber;
        this.destination = destination;
        this.distance = distance;
        this.plateNumber = plateNumber;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getTicketNumber() { return ticketNumber; }
    public int getSeatNumber() { return seatNumber; }
    public String getDestination() { return destination; }
    public double getDistance() { return distance; }
    public String getPlateNumber() { return plateNumber; }
    
    @Override
    public String toString() {
        return "Passenger Details:\n" +
                "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Ticket Number: " + ticketNumber + "\n" +
                "Seat Number: " + seatNumber + "\n" +
                "Destination: " + destination + "\n" +
                "Distance: " + distance + " km\n" +
                "Bus Plate Number: " + plateNumber;
    }
}

// Reservation class implements PassengerManagement to handle booking validations.
class Reservation implements PassengerManagement {
    private static final int MAX_SEATS = 50;
    // Store all booked passengers
    private static List<Passenger> passengerList = new ArrayList<>();
    
    // Accessor for the passenger list.
    public static List<Passenger> getPassengerList() {
        return passengerList;
    }
    
    @Override
    public void bookTicket(String passengerId, int seatNumber, String destination, double distance)
            throws BookingException {
        // Check if bus capacity is reached.
        if (passengerList.size() >= MAX_SEATS) {
            throw new BookingException("Bus is fully booked. No more seats available.");
        }
        // Ensure the seat is not already booked.
        for (Passenger p : passengerList) {
            if (p.getSeatNumber() == seatNumber) {
                throw new BookingException("Seat " + seatNumber + " is already booked.");
            }
        }
        System.out.println("Seat " + seatNumber + " is available for booking.");
    }
    
    @Override
    public double calculateFare(double distance, boolean isDiscounted) {
        // Not implemented in Reservation; use Ticketing.
        throw new UnsupportedOperationException("Use Ticketing for fare calculation.");
    }
    
    @Override
    public void trackBus(String busNumber) {
        // Not implemented in Reservation; use BusTracking.
        throw new UnsupportedOperationException("Use BusTracking for bus tracking.");
    }
}

// Ticketing class implements PassengerManagement for fare calculation.
class Ticketing implements PassengerManagement {
    @Override
    public void bookTicket(String passengerId, int seatNumber, String destination, double distance) {
        throw new UnsupportedOperationException("Use Reservation for booking.");
    }
    
    @Override
    public double calculateFare(double distance, boolean isDiscounted) {
        double ratePerKm = 200000.0; // 200K per km
        double fare = distance * ratePerKm;
        return isDiscounted ? fare * 0.8 : fare;
    }
    
    @Override
    public void trackBus(String busNumber) {
        throw new UnsupportedOperationException("Use BusTracking for bus tracking.");
    }
}

// BusTracking class implements PassengerManagement for tracking bus schedules.
class BusTracking implements PassengerManagement {
    @Override
    public void bookTicket(String passengerId, int seatNumber, String destination, double distance) {
        throw new UnsupportedOperationException("Booking not handled in BusTracking.");
    }
    
    @Override
    public double calculateFare(double distance, boolean isDiscounted) {
        throw new UnsupportedOperationException("Ticketing not handled in BusTracking.");
    }
    
    @Override
    public void trackBus(String busNumber) {
        // Simulate bus tracking (in a real system, real-time data would be used)
        System.out.println("Tracking Bus " + busNumber + ":\n"
                + "Current Location: Central Bus Station\n"
                + "Next Stop: Downtown in 10 minutes.\n"
                + "Scheduled Arrival: 15:30.");
    }
}

// Main class with a menu-driven terminal application.
public class BusPassengerManagementSystem {
    public static void main(String[] args) {
        // Create instances of our management classes
        Reservation reservation = new Reservation();
        Ticketing ticketing = new Ticketing();
        BusTracking busTracking = new BusTracking();
        
        // Use try-with-resources for the Scanner to ensure proper resource management.
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                printMenu();
                System.out.print("Select an option: ");
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":  // Book Ticket
                        try {
                            System.out.print("Enter Passenger ID: ");
                            String id = scanner.nextLine();
                            
                            System.out.print("Enter Full Name: ");
                            String name = scanner.nextLine();
                            
                            System.out.print("Enter Age: ");
                            int age = Integer.parseInt(scanner.nextLine());
                            if (age < 6) {
                                throw new BookingException("Passenger must be at least 6 years old to travel alone.");
                            }
                            
                            System.out.print("Enter Ticket Number: ");
                            int ticketNumber = Integer.parseInt(scanner.nextLine());
                            // Ensure ticket number is unique
                            for (Passenger p : Reservation.getPassengerList()) {
                                if (p.getTicketNumber() == ticketNumber) {
                                    throw new BookingException("Ticket number already exists for another booking.");
                                }
                            }
                            
                            System.out.print("Enter Seat Number (1-50): ");
                            int seatNumber = Integer.parseInt(scanner.nextLine());
                            if (seatNumber < 1 || seatNumber > 50) {
                                throw new BookingException("Seat number must be between 1 and 50.");
                            }
                            
                            System.out.print("Enter Destination: ");
                            String destination = scanner.nextLine();
                            
                            System.out.print("Enter Distance (in km): ");
                            double distance = Double.parseDouble(scanner.nextLine());
                            if (distance <= 0) {
                                throw new BookingException("Distance must be a positive number.");
                            }
                            
                            System.out.print("Enter Bus Plate Number: ");
                            String plateNumber = scanner.nextLine();
                            
                            // Check booking constraints via Reservation
                            reservation.bookTicket(id, seatNumber, destination, distance);
                            
                            // Check if seat is available and capacity is not exceeded;
                            // now create the Passenger object.
                            Passenger newPassenger = new Passenger(id, name, age, ticketNumber, seatNumber, destination, distance, plateNumber);
                            
                            // Add passenger to the Reservation list
                            Reservation.getPassengerList().add(newPassenger);
                            
                            // Determine if discount applies (for simplicity, discount for age 60 or above)\n                            boolean isDiscounted = age >= 60;  // You could extend logic to include student discounts\n                            double fare = ticketing.calculateFare(distance, isDiscounted);\n\n                            // Display booking confirmation\n                            System.out.println(\"\\nBooking Successful!\");\n                            System.out.println(newPassenger);\n                            System.out.println(String.format(\"Fare to pay: %.0f VND\", fare));\n                        } catch (NumberFormatException e) {\n                            System.out.println(\"Invalid input! Please enter numbers where required.\");\n                        } catch (BookingException e) {\n                            System.out.println(\"Booking Error: \" + e.getMessage());\n                        } catch (Exception e) {\n                            System.out.println(\"An unexpected error occurred: \" + e.getMessage());\n                        }\n                        break;\n                    case \"2\":  // Track Bus\n                        System.out.print(\"Enter Bus Number: \");\n                        String busNumber = scanner.nextLine();\n                        busTracking.trackBus(busNumber);\n                        break;\n                    case \"3\":  // List all bookings\n                        List<Passenger> list = Reservation.getPassengerList();\n                        if (list.isEmpty()) {\n                            System.out.println(\"No bookings have been made yet.\");\n                        } else {\n                            System.out.println(\"\\n--- All Bookings ---\");\n                            for (Passenger p : list) {\n                                System.out.println(p);\n                                // Calculate fare for display\n                                boolean isDiscounted = p.getAge() >= 60;\n                                double fare = ticketing.calculateFare(p.getDistance(), isDiscounted);\n                                System.out.println(String.format(\"Fare: %.0f VND\", fare));\n                                System.out.println(\"-----------------------\");\n                            }\n                        }\n                        break;\n                    case \"4\":  // Exit\n                        exit = true;\n                        System.out.println(\"Exiting the Bus Passenger Management System. Goodbye!\");\n                        break;\n                    default:\n                        System.out.println(\"Invalid choice! Please try again.\");\n                }\n            }\n        } catch (Exception e) {\n            System.out.println(\"An unexpected error occurred in the system: \" + e.getMessage());\n        }\n    }\n\n    // Prints the main menu options\n    private static void printMenu() {\n        System.out.println(\"\\n--- Bus Passenger Management System Menu ---\");\n        System.out.println(\"1. Book Ticket\");\n        System.out.println(\"2. Track Bus\");\n        System.out.println(\"3. List All Bookings\");\n        System.out.println(\"4. Exit\");\n    }\n}\n```

---

### Explanation of the Code

- **Interface & Implementations:**  
  - The `PassengerManagement` interface declares three methods.  
  - The `Reservation` class handles the ticket booking validations.  
  - The `Ticketing` class is responsible for fare calculation based on a fixed rate (200,000 VND/km) with a 20% discount for passengers aged 60 or above.  
  - The `BusTracking` class simulates tracking by printing a dummy schedule.

- **Passenger Class:**  
  Uses encapsulation to store passenger details with private fields, and provides a `toString()` method to display all the details.

- **Validations and Exception Handling:**  
  - Custom `BookingException` is thrown when business rules are violated (e.g., seat already booked, invalid age, duplicate ticket number).  
  - Input validations check that numeric values are entered properly and are within expected ranges.

- **Collections:**  
  An `ArrayList` is used (inside `Reservation`) to store all bookings.

- **Menu–Driven Application:**  
  The `main` method (in `BusPassengerManagementSystem`) presents a menu to the user, allowing them to book tickets, track buses, or list all bookings. It uses try–with–resources to manage the `Scanner`.

This complete code should compile and run as a stand–alone Java application. You can enhance it further by adding more features, such as persistent storage, more refined discount logic, or real–time bus tracking data.
