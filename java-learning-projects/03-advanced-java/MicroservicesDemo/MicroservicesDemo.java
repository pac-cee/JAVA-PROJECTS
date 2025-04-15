// MicroservicesDemo
// Demonstrates a simple microservices architecture using Spring Boot.
// Service 1: UserService
// Service 2: OrderService (calls UserService)

// UserServiceApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable int id) {
        return "User-" + id;
    }
}

// OrderServiceApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RestController
class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable int orderId) {
        // In a real microservice, this would call UserService via service discovery
        String user = restTemplate().getForObject("http://localhost:8081/user/1", String.class);
        return "Order-" + orderId + " by " + user;
    }
}
