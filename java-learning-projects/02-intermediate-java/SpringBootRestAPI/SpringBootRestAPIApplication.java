// SpringBootRestAPIApplication.java
// Demonstrates a simple REST API using Spring Boot.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SpringBootRestAPIApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestAPIApplication.class, args);
    }

    // GET endpoint: /hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot REST API!";
    }
}
