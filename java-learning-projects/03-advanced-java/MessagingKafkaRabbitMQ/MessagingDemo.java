// MessagingDemo.java
// Demonstrates messaging concepts with Kafka and RabbitMQ (conceptual, not runnable without brokers)
// Shows how to send and receive messages using Spring Boot

// Kafka Example
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
class KafkaDemo {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/kafka/send")
    public String sendKafka(@RequestParam String msg) {
        kafkaTemplate.send("demo-topic", msg);
        return "Sent to Kafka: " + msg;
    }

    @KafkaListener(topics = "demo-topic")
    public void listenKafka(String msg) {
        System.out.println("Received from Kafka: " + msg);
    }
}

// RabbitMQ Example
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RestController
class RabbitMQDemo {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/rabbit/send")
    public String sendRabbit(@RequestParam String msg) {
        rabbitTemplate.convertAndSend("demo-queue", msg);
        return "Sent to RabbitMQ: " + msg;
    }

    @RabbitListener(queues = "demo-queue")
    public void listenRabbit(String msg) {
        System.out.println("Received from RabbitMQ: " + msg);
    }
}
