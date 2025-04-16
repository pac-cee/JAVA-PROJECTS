# Java Banking System (Swing UI)

A fully functional banking system built in Java using OOP, collections, custom exceptions, and a desktop GUI (Swing). The app can be run locally or inside Docker (with GUI forwarding).

## Features
- Create, deposit, withdraw, transfer between accounts
- Account types: Savings, Checking (with overdraft/min balance rules)
- In-memory storage using Java collections
- Robust exception handling
- Simple, intuitive Swing-based UI
- Docker support for containerized desktop execution

## Project Structure
```
banking-system/
  ├── src/main/java/com/example/bank/
  │     ├── BankApp.java         # Main UI
  │     ├── model/Account.java, SavingsAccount.java, CheckingAccount.java, Customer.java, Transaction.java
  │     ├── service/BankService.java
  │     └── exception/InsufficientFundsException.java
  ├── pom.xml
  └── Dockerfile
```

## Prerequisites
- Java 17+
- Maven 3.6+
- (For Docker GUI) X server (e.g., Xming, VcXsrv on Windows)
- Docker Desktop

## How to Build & Run

### Locally
```sh
cd banking-system
mvn clean package
java -jar target/banking-system-0.0.1-SNAPSHOT.jar
```

### With Docker (GUI)
1. Start your X server (e.g., Xming/VcXsrv) on your host.
2. Build and run the container:
```sh
docker build -t banking-system .
docker run -e DISPLAY=host.docker.internal:0 -v /tmp/.X11-unix:/tmp/.X11-unix banking-system
```
- The Swing UI will appear on your desktop.

## Notes
- All data is in-memory (reset on restart).
- For persistent storage, integrate with a database (ask for help if you want this!).
- For RESTful or web UI, ask for an extension!

---
Enjoy learning Java with a real banking app!
