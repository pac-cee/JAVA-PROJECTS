# Twitter Bot (Java + Spring Boot)

This project is a starter template for a Twitter bot using Java and Spring Boot. It uses the Twitter4J library to interact with the Twitter API.

## Features
- Tweet automatically (scheduled or via REST endpoint)
- Like, follow, search tweets (extendable)

## Getting Started

### 1. Prerequisites
- Java 17+
- Maven
- Twitter Developer Account (for API keys)

### 2. Setup
1. Clone/download this repo.
2. Fill in your Twitter API credentials in `src/main/resources/application.properties`:
   ```properties
   twitter4j.oauth.consumerKey=YOUR_CONSUMER_KEY
   twitter4j.oauth.consumerSecret=YOUR_CONSUMER_SECRET
   twitter4j.oauth.accessToken=YOUR_ACCESS_TOKEN
   twitter4j.oauth.accessTokenSecret=YOUR_ACCESS_TOKEN_SECRET
   ```
3. Build and run the application:
   ```sh
   mvn spring-boot:run
   ```

### 3. Usage
- The bot exposes REST endpoints (to be implemented) for triggering actions like tweeting, liking, etc.

## Extending
- Add controllers and services under `com.example.twitterbot.controller` and `com.example.twitterbot.service`.

---

## License
MIT
