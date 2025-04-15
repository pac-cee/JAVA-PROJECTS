# MessagingKafkaRabbitMQ Project

This project demonstrates messaging with Kafka and RabbitMQ using Spring Boot.

## Business Logic / Explanation

- **Kafka:**
  - `KafkaDemo` controller sends messages to the `demo-topic` topic and listens for messages from it.
  - Uses `KafkaTemplate` to send and `@KafkaListener` to receive.
- **RabbitMQ:**
  - `RabbitMQDemo` controller sends messages to the `demo-queue` queue and listens for messages from it.
  - Uses `RabbitTemplate` to send and `@RabbitListener` to receive.
- **How it works:**
  - Send a POST request to `/kafka/send?msg=Hello` or `/rabbit/send?msg=Hello` to produce messages.
  - Listeners print received messages to the console.

## How to Run
1. Ensure Kafka and/or RabbitMQ brokers are running locally.
2. Add the required dependencies to your `pom.xml`:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-amqp</artifactId>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-kafka</artifactId>
   </dependency>
   ```
3. Compile and run the application:
   ```
   mvn spring-boot:run
   ```
4. Test by sending POST requests (e.g., via Postman or curl):
   ```
   curl -X POST "http://localhost:8080/kafka/send?msg=HelloKafka"
   curl -X POST "http://localhost:8080/rabbit/send?msg=HelloRabbit"
   ```

Expected output (console):
```
Received from Kafka: HelloKafka
Received from RabbitMQ: HelloRabbit
```

---

This example introduces asynchronous communication, a key pattern in scalable backend systems.
