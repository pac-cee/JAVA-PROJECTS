# Blockchain Explorer

## Description
A backend API for exploring blockchain transactions, blocks, and addresses.

## Features
- View blockchain transactions and blocks
- Search by address or hash
- RESTful endpoints

## Technologies Used
- Java 17+
- Spring Boot
- Web3j/BitcoinJ

## Setup Instructions
1. Install Java and Maven
2. Configure blockchain node/API in `application.properties`
3. Run: `mvn spring-boot:run`

## API Documentation
- `/api/blocks` - Get blocks
- `/api/transactions` - Get transactions
- `/api/addresses` - Get address info

## Architecture
Layered architecture: Controller, Service, Repository.

## Usage Examples
See API docs above.

## Contribution
Fork, branch, PR.

## License
MIT
