package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class CustomErrorController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(WebRequest webRequest) {
        Map<String, Object> body = new HashMap<>();
        Throwable error = errorAttributes.getError(webRequest);
        int status = (int) errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults()).getOrDefault("status", 500);
        String message = (String) errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults()).getOrDefault("error", "Unexpected error");
        body.put("error", message);
        body.put("status", status);
        if (error != null) {
            body.put("message", error.getMessage());
        }
        return ResponseEntity.status(status).body(body);
    }
}
