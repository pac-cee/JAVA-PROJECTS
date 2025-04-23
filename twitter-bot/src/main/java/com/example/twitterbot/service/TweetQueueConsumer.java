package com.example.twitterbot.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TweetQueueConsumer {
    @Autowired
    private TwitterService twitterService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = "tweetQueue")
    public void receiveTweet(String message) {
        String result = twitterService.tweet(message);
        // Send real-time notification to clients via WebSocket
        messagingTemplate.convertAndSend("/topic/tweetStatus", result);
    }
}
