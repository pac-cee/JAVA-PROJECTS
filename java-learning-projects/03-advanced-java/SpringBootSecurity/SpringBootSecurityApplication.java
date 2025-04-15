// SpringBootSecurityApplication.java
// Secures a REST endpoint with basic authentication using Spring Security.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@RestController
public class SpringBootSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityApplication.class, args);
    }

    // Secured endpoint
    @GetMapping("/secure")
    public String secureEndpoint() {
        return "This is a secured endpoint!";
    }

    // Security configuration
    @EnableWebSecurity
    public static class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .httpBasic(); // Basic Auth
            return http.build();
        }
    }
}
