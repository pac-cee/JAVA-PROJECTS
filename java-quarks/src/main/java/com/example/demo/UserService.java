package com.example.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public User createUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }

    public User getUserById(String id) {
        return users.get(id);
    }
}
