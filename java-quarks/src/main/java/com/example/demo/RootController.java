package com.example.demo;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok(Map.of(
            "message", "User Management API. Use POST /users and GET /users/{id}."
        ));
    }
}
