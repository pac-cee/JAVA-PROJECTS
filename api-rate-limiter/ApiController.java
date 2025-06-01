import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/api/ratelimit")
    public String rateLimitedEndpoint() {
        return "Request successful!";
    }
}