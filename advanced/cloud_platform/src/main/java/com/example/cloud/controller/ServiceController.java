package com.example.cloud.controller;

import com.example.cloud.model.ServiceRequest;
import com.example.cloud.model.ServiceResponse;
import com.example.cloud.service.ServiceOrchestrator;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceOrchestrator serviceOrchestrator;

    @PostMapping
    @CircuitBreaker(name = "serviceRequest")
    @RateLimiter(name = "serviceRequest")
    public Mono<ServiceResponse> handleRequest(@RequestBody ServiceRequest request) {
        return serviceOrchestrator.processRequest(request)
            .doOnSuccess(response -> 
                log.info("Request processed successfully: {}", response)
            )
            .doOnError(error ->
                log.error("Error processing request: {}", error.getMessage())
            );
    }

    @GetMapping("/stream")
    public Flux<ServiceResponse> streamResponses() {
        return serviceOrchestrator.getResponseStream()
            .doOnNext(response ->
                log.debug("Streaming response: {}", response)
            );
    }
} 