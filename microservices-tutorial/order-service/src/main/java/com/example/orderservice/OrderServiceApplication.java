package com.example.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    // Enable load-balanced RestTemplate for Eureka service discovery
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@RestController
class OrderController {
    private final RestTemplate restTemplate;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/orders")
    public List<String> getOrders() {
        return List.of("Order1", "Order2", "Order3");
    }

    @GetMapping("/orders-with-users")
    @CircuitBreaker(name = "userService", fallbackMethod = "userServiceFallback")
    public Map<String, Object> getOrdersWithUsers() {
        List<String> orders = getOrders();
        // Call user-service via Eureka
        List users = restTemplate.getForObject("http://user-service/users", List.class);
        return Map.of("orders", orders, "users", users);
    }

    // Fallback method if user-service is unavailable
    public Map<String, Object> userServiceFallback(Throwable t) {
        List<String> orders = getOrders();
        return Map.of(
            "orders", orders,
            "users", List.of("User service unavailable")
        );
    }
}
