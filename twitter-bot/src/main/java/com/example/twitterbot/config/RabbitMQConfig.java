package com.example.twitterbot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String TWEET_QUEUE = "tweetQueue";

    @Bean
    public Queue tweetQueue() {
        return new Queue(TWEET_QUEUE, false);
    }
}
