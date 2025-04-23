package com.example.twitterbot.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.twitterbot.config.RabbitMQConfig;

@Service
public class TweetQueueProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTweetToQueue(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TWEET_QUEUE, message);
    }
}
