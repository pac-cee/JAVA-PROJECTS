// RedisCachingDemo.java
// Demonstrates basic Redis caching with Spring Boot.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableCaching
@RestController
public class RedisCachingDemo {
    public static void main(String[] args) {
        SpringApplication.run(RedisCachingDemo.class, args);
    }

    // A cached endpoint
    @GetMapping("/expensive/{input}")
    @Cacheable("expensive")
    public String expensiveOperation(@PathVariable String input) {
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
        return "Processed: " + input;
    }
}
