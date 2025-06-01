import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final int MAX_REQUESTS = 100; // Max requests per minute
    private static final Duration TIME_WINDOW = Duration.ofMinutes(1);

    public boolean isAllowed(String key) {
        String redisKey = "rate_limit:" + key;
        Long currentCount = redisTemplate.opsForValue().increment(redisKey);

        if (currentCount == 1) {
            // Set expiration for the key
            redisTemplate.expire(redisKey, TIME_WINDOW);
        }

        return currentCount <= MAX_REQUESTS;
    }
}