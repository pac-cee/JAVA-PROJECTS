package com.example.apigateway.controller;

import java.util.Map;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/gateway")
public class GatewayManagementController {

    private final RouteLocator routeLocator;

    public GatewayManagementController(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @GetMapping("/routes")
    public ResponseEntity<Flux<Map<String, Object>>> getRoutes() {
        Flux<Map<String, Object>> routes = routeLocator.getRoutes()
            .map(route -> Map.of(
                "id", route.getId(),
                "uri", route.getUri().toString(),
                "predicates", route.getPredicate().toString(),
                "filters", route.getFilters().toString()
            ));
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
