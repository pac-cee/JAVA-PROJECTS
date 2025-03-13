package com.example.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Cloud-Native Microservices Platform
 * Features:
 * - Service Discovery (Eureka)
 * - API Gateway (Spring Cloud Gateway)
 * - Circuit Breaking (Resilience4j)
 * - Distributed Tracing (Sleuth + Zipkin)
 * - Event Streaming (Kafka)
 * - Reactive Programming (WebFlux)
 * - OAuth2 Security
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableWebFlux
@EnableBinding(Source.class)
@EnableReactiveMongoRepositories
public class CloudPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudPlatformApplication.class, args);
    }
} 