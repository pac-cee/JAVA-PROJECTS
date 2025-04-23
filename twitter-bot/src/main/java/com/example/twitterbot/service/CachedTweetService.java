package com.example.twitterbot.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CachedTweetService {
    // Example cacheable method
    @Cacheable("tweets")
    public String getTweetById(Long id) {
        // Simulate fetching from DB or Twitter API
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        return "Tweet content for id: " + id;
    }
}
