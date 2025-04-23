package com.example.twitterbot.controller;

import com.example.twitterbot.service.TweetQueueProducer;
import io.github.bucket4j.Bucket;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bot")
public class BotController {
    @Autowired
    private TweetQueueProducer tweetQueueProducer;

    @Autowired
    @Qualifier("tweetApiBucket")
    private Bucket tweetApiBucket;

    @PostMapping("/tweet")
    @CircuitBreaker(name = "twitterService", fallbackMethod = "fallbackTweet")
    public String tweet(@RequestParam String message) {
        if (tweetApiBucket.tryConsume(1)) {
            tweetQueueProducer.sendTweetToQueue(message);
            return "Tweet request queued!";
        } else {
            return "Rate limit exceeded. Please try again later.";
        }
    }

    public String fallbackTweet(String message, Throwable t) {
        return "Service temporarily unavailable. Please try again later.";
    }
}
