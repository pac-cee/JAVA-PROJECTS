package com.example.twitterbot.service;

import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Value;

@Service
public class TwitterService {
    private Twitter twitter;

    public TwitterService(
            @Value("${twitter4j.oauth.consumerKey}") String consumerKey,
            @Value("${twitter4j.oauth.consumerSecret}") String consumerSecret,
            @Value("${twitter4j.oauth.accessToken}") String accessToken,
            @Value("${twitter4j.oauth.accessTokenSecret}") String accessTokenSecret
    ) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey(consumerKey)
          .setOAuthConsumerSecret(consumerSecret)
          .setOAuthAccessToken(accessToken)
          .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public String tweet(String message) {
        try {
            twitter.updateStatus(message);
            return "Tweet posted successfully!";
        } catch (TwitterException e) {
            return "Failed to post tweet: " + e.getMessage();
        }
    }
}
