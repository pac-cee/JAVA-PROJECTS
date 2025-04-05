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



////////////////////////////////////////////////////////
///////////////////////////////////////////////////////




import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

// Interface for passenger management
interface PassengerManagement {
    boolean bookTicket(String passengerId, int seatNumber, String destination, double distance);
    double calculateFare(double distance, boolean isDiscounted);
    void trackBus(String busNumber);
}

// Passenger class with encapsulation
class Passenger {
    private String id;
    private String name;
    private int age;
    private int ticketNumber;
    private int seatNumber;
    private String destination;
    private double distance;
    private String plateNumber;
    
    // Constructor
    public Passenger(String id, String name, int age, int ticketNumber, int seatNumber, 
                     String destination, double distance, String plateNumber) {
        this.id = id;
        this.name = name;
        
        // Age validation
        if (age < 6) {
            throw new IllegalArgumentException("Passengers below 6 years are not allowed to travel alone.");
        }
        this.age = age;
        
        this.ticketNumber = ticketNumber;
        
        // Seat number validation
        if (seatNumber < 1 || seatNumber > 50) {
            throw new IllegalArgumentException("Seat number must be between 1 and 50.");
        }
        this.seatNumber = seatNumber;
        
        this.destination = destination;
        
        // Distance validation
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero.");
        }
        this.distance = distance;
        
        this.plateNumber = plateNumber;
    }
    
    // Getters and setters with validation
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Passenger ID cannot be empty.");
        }
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Passenger name cannot be empty.");
        }
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        if (age < 6) {
            throw new IllegalArgumentException("Passengers below 6 years are not allowed to travel alone.");
        }
        this.age = age;
    }
    
    public int getTicketNumber() {
        return ticketNumber;
    }
    
    public void setTicketNumber(int ticketNumber) {
        if (ticketNumber <= 0) {
            throw new IllegalArgumentException("Ticket number must be positive.");
        }
        this.ticketNumber = ticketNumber;
    }
    
    public int getSeatNumber() {
        return seatNumber;
    }
    
    public void setSeatNumber(int seatNumber) {
        if (seatNumber < 1 || seatNumber > 50) {
            throw new IllegalArgumentException("Seat number must be between 1 and 50.");
        }
        this.seatNumber = seatNumber;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        if (destination == null || destination.isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be empty.");
        }
        this.destination = destination;
    }
    
    public double getDistance() {
        return distance;
    }
    
    public void setDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero.");
        }
        this.distance = distance;
    }
    
    public String getPlateNumber() {
        return plateNumber;
    }
    
    public void setPlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.isEmpty()) {
            throw new IllegalArgumentException("Bus plate number cannot be empty.");
        }
        this.plateNumber = plateNumber;
    }
    
    // Is passenger eligible for discount
    public boolean isEligibleForDiscount() {
        return age <= 18 || age >= 60; // Student or Senior Citizen
    }
    
    // Display passenger information
    public void displayInfo() {
        System.out.println("\n======== Passenger Information ========");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Destination: " + destination);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Bus Plate Number: " + plateNumber);
        System.out.println("=====================================");
    }
}

// Reservation class that implements PassengerManagement
class Reservation implements PassengerManagement {
    private Map<String, Map<Integer, Boolean>> busSeats; // Maps bus plate number to seat availability
    private ArrayList<Passenger> passengers;
    private static int nextTicketNumber = 1000;
    
    public Reservation() {
        busSeats = new HashMap<>();
        passengers = new ArrayList<>();
    }
    
    // Initialize a new bus with all seats available
    public void initializeBus(String plateNumber) {
        Map<Integer, Boolean> seats = new HashMap<>();
        for (int i = 1; i <= 50; i++) {
            seats.put(i, true); // true means seat is available
        }
        busSeats.put(plateNumber, seats);
        System.out.println("Bus " + plateNumber + " initialized with 50 available seats.");
    }
    
    // Check if a seat is available
    public boolean isSeatAvailable(String plateNumber, int seatNumber) {
        if (!busSeats.containsKey(plateNumber)) {
            throw new IllegalArgumentException("Bus with plate number " + plateNumber + " does not exist.");
        }
        
        Map<Integer, Boolean> seats = busSeats.get(plateNumber);
        if (!seats.containsKey(seatNumber)) {
            throw new IllegalArgumentException("Invalid seat number: " + seatNumber);
        }
        
        return seats.get(seatNumber);
    }
    
    @Override
    public boolean bookTicket(String passengerId, int seatNumber, String destination, double distance) {
        try {
            // This method is implemented in the main system
            return false;
        } catch (Exception e) {
            System.out.println("Error booking ticket: " + e.getMessage());
            return false;
        }
    }
    
    // Book a specific seat
    public boolean bookSeat(String plateNumber, int seatNumber, Passenger passenger) {
        if (!busSeats.containsKey(plateNumber)) {
            throw new IllegalArgumentException("Bus with plate number " + plateNumber + " does not exist.");
        }
        
        Map<Integer, Boolean> seats = busSeats.get(plateNumber);
        if (!seats.containsKey(seatNumber)) {
            throw new IllegalArgumentException("Invalid seat number: " + seatNumber);
        }
        
        if (!seats.get(seatNumber)) {
            throw new IllegalArgumentException("Seat " + seatNumber + " is already booked.");
        }
        
        // Book the seat
        seats.put(seatNumber, false); // false means seat is booked
        passengers.add(passenger);
        
        System.out.println("Seat " + seatNumber + " booked successfully for passenger " + passenger.getName() + ".");
        return true;
    }
    
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
    
    public static int generateTicketNumber() {
        return nextTicketNumber++;
    }
    
    @Override
    public double calculateFare(double distance, boolean isDiscounted) {
        double basicFare = distance * 200; // 200K per km
        
        if (isDiscounted) {
            return basicFare * 0.8; // 20% discount
        } else {
            return basicFare;
        }
    }
    
    @Override
    public void trackBus(String busNumber) {
        System.out.println("Tracking bus " + busNumber + "...");
        System.out.println("This functionality is handled by the BusTracking class.");
    }
    
    // Display available seats for a bus
    public void displayAvailableSeats(String plateNumber) {
        if (!busSeats.containsKey(plateNumber)) {
            System.out.println("Bus with plate number " + plateNumber + " does not exist.");
            return;
        }
        
        Map<Integer, Boolean> seats = busSeats.get(plateNumber);
        System.out.println("\n===== Available Seats for Bus " + plateNumber + " =====");
        
        int count = 0;
        for (int i = 1; i <= 50; i++) {
            if (seats.get(i)) {
                System.out.print(i + " ");
                count++;
                if (count % 10 == 0) {
                    System.out.println();
                }
            }
        }
        
        if (count == 0) {
            System.out.println("No seats available on this bus.");
        } else {
            System.out.println("\nTotal available seats: " + count);
        }
        System.out.println("===========================================");
    }
}

// Ticketing class that implements PassengerManagement
class Ticketing implements PassengerManagement {
    private Reservation reservation;
    
    public Ticketing(Reservation reservation) {
        this.reservation = reservation;
    }
    
    @Override
    public boolean bookTicket(String passengerId, int seatNumber, String destination, double distance) {
        // This is implemented in the main system
        return false;
    }
    
    @Override
    public double calculateFare(double distance, boolean isDiscounted) {
        return reservation.calculateFare(distance, isDiscounted);
    }
    
    @Override
    public void trackBus(String busNumber) {
        System.out.println("Tracking bus " + busNumber + "...");
        System.out.println("This functionality is handled by the BusTracking class.");
    }
    
    // Print ticket details
    public void printTicket(Passenger passenger) {
        double fare = calculateFare(passenger.getDistance(), passenger.isEligibleForDiscount());
        
        System.out.println("\n=========== TICKET ===========");
        System.out.println("Ticket Number: " + passenger.getTicketNumber());
        System.out.println("Passenger Name: " + passenger.getName());
        System.out.println("Passenger ID: " + passenger.getId());
        System.out.println("Age: " + passenger.getAge());
        System.out.println("Bus Plate Number: " + passenger.getPlateNumber());
        System.out.println("Seat Number: " + passenger.getSeatNumber());
        System.out.println("Destination: " + passenger.getDestination());
        System.out.println("Distance: " + passenger.getDistance() + " km");
        System.out.println("Fare: " + fare + " RWF");
        if (passenger.isEligibleForDiscount()) {
            System.out.println("(20% discount applied)");
        }
        System.out.println("============================");
    }
}

// BusTracking class that implements PassengerManagement
class BusTracking implements PassengerManagement {
    private Map<String, String> busLocations;
    private Map<String, String> busSchedules;
    
    public BusTracking() {
        busLocations = new HashMap<>();
        busSchedules = new HashMap<>();
    }
    
    public void updateBusLocation(String busNumber, String location) {
        busLocations.put(busNumber, location);
        System.out.println("Bus " + busNumber + " location updated to " + location);
    }
    
    public void updateBusSchedule(String busNumber, String schedule) {
        busSchedules.put(busNumber, schedule);
        System.out.println("Bus " + busNumber + " schedule updated to " + schedule);
    }
    
    @Override
    public boolean bookTicket(String passengerId, int seatNumber, String destination, double distance) {
        System.out.println("Booking functionality is handled by Reservation class.");
        return false;
    }
    
    @Override
    public double calculateFare(double distance, boolean isDiscounted) {
        System.out.println("Fare calculation is handled by Ticketing class.");
        return 0;
    }
    
    @Override
    public void trackBus(String busNumber) {
        if (busLocations.containsKey(busNumber)) {
            System.out.println("\n===== Bus Tracking Information =====");
            System.out.println("Bus Number: " + busNumber);
            System.out.println("Current Location: " + busLocations.get(busNumber));
            
            if (busSchedules.containsKey(busNumber)) {
                System.out.println("Schedule: " + busSchedules.get(busNumber));
            } else {
                System.out.println("No schedule information available.");
            }
            System.out.println("===================================");
        } else {
            System.out.println("No tracking information available for bus " + busNumber);
        }
    }
}

// Main class for the Bus Passenger Management System
public class BusPassengerManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Reservation reservation = new Reservation();
    private static Ticketing ticketing = new Ticketing(reservation);
    private static BusTracking busTracking = new BusTracking();
    
    public static void main(String[] args) {
        System.out.println("===== Bus Passenger Management System =====");
        
        boolean exit = false;
        while (!exit) {
            try {
                printMenu();
                int choice = getIntInput("Enter your choice: ");
                
                switch (choice) {
                    case 1:
                        initializeNewBus();
                        break;
                    case 2:
                        bookTicket();
                        break;
                    case 3:
                        displayAvailableSeats();
                        break;
                    case 4:
                        trackBus();
                        break;
                    case 5:
                        displayAllPassengers();
                        break;
                    case 6:
                        updateBusLocation();
                        break;
                    case 7:
                        exit = true;
                        System.out.println("Thank you for using the Bus Passenger Management System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
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
    
    private static void printMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Initialize New Bus");
        System.out.println("2. Book a Ticket");
        System.out.println("3. Display Available Seats");
        System.out.println("4. Track Bus");
        System.out.println("5. Display All Passengers");
        System.out.println("6. Update Bus Location");
        System.out.println("7. Exit");
    }
    
    private static void initializeNewBus() {
        String plateNumber = getStringInput("Enter bus plate number: ");
        reservation.initializeBus(plateNumber);
    }
    
    private static void bookTicket() {
        try {
            String plateNumber = getStringInput("Enter bus plate number: ");
            
            // Display available seats first
            reservation.displayAvailableSeats(plateNumber);
            
            String passengerId = getStringInput("Enter passenger ID: ");
            String name = getStringInput("Enter passenger name: ");
            
            int age = getIntInput("Enter passenger age: ");
            if (age < 6) {
                System.out.println("Passengers below 6 years are not allowed to travel alone.");
                return;
            }
            
            int seatNumber = getIntInput("Enter seat number (1-50): ");
            if (seatNumber < 1 || seatNumber > 50) {
                System.out.println("Seat number must be between 1 and 50.");
                return;
            }
            
            // Check if seat is available
            if (!reservation.isSeatAvailable(plateNumber, seatNumber)) {
                System.out.println("Seat " + seatNumber + " is already booked. Please choose another seat.");
                return;
            }
            
            String destination = getStringInput("Enter destination: ");
            double distance = getDoubleInput("Enter distance (in km): ");
            
            if (distance <= 0) {
                System.out.println("Distance must be greater than zero.");
                return;
            }
            
            int ticketNumber = Reservation.generateTicketNumber();
            
            // Create passenger object
            Passenger passenger = new Passenger(
                passengerId, name, age, ticketNumber, seatNumber, destination, distance, plateNumber
            );
            
            // Book the seat
            boolean success = reservation.bookSeat(plateNumber, seatNumber, passenger);
            
            if (success) {
                // Calculate and display fare
                double fare = ticketing.calculateFare(distance, passenger.isEligibleForDiscount());
                System.out.println("\nBooking successful!");
                System.out.println("Ticket Number: " + ticketNumber);
                System.out.println("Amount to Pay: " + fare + " RWF");
                
                if (passenger.isEligibleForDiscount()) {
                    System.out.println("(20% discount applied for student/senior)");
                }
                
                // Print ticket
                ticketing.printTicket(passenger);
            }
        } catch (Exception e) {
            System.out.println("Error booking ticket: " + e.getMessage());
        }
    }
    
    private static void displayAvailableSeats() {
        String plateNumber = getStringInput("Enter bus plate number: ");
        reservation.displayAvailableSeats(plateNumber);
    }
    
    private static void trackBus() {
        String plateNumber = getStringInput("Enter bus plate number: ");
        busTracking.trackBus(plateNumber);
    }
    
    private static void displayAllPassengers() {
        ArrayList<Passenger> passengers = reservation.getPassengers();
        
        if (passengers.isEmpty()) {
            System.out.println("No passengers booked yet.");
            return;
        }
        
        System.out.println("\n===== All Passengers =====");
        for (Passenger passenger : passengers) {
            passenger.displayInfo();
            
            // Calculate and display fare
            double fare = ticketing.calculateFare(passenger.getDistance(), passenger.isEligibleForDiscount());
            System.out.println("Fare: " + fare + " RWF");
            if (passenger.isEligibleForDiscount()) {
                System.out.println("(20% discount applied)");
            }
        }
    }
    
    private static void updateBusLocation() {
        String plateNumber = getStringInput("Enter bus plate number: ");
        String location = getStringInput("Enter current location: ");
        String schedule = getStringInput("Enter schedule (optional, press Enter to skip): ");
        
        busTracking.updateBusLocation(plateNumber, location);
        
        if (!schedule.isEmpty()) {
            busTracking.updateBusSchedule(plateNumber, schedule);
        }
    }
    
    // Helper methods for input validation
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
    
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}