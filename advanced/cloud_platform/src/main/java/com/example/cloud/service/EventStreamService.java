package com.example.cloud.service;

import com.example.cloud.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

@Service
@RequiredArgsConstructor
public class EventStreamService {
    
    private final Source source;
    private final KafkaReceiver<String, Event> kafkaReceiver;
    
    public Mono<Void> publishEvent(Event event) {
        return Mono.fromRunnable(() -> 
            source.output().send(
                MessageBuilder.withPayload(event)
                    .setHeader("type", event.getType())
                    .build()
            )
        );
    }
    
    public Flux<Event> subscribeToEvents(String topic) {
        return kafkaReceiver.receive()
            .map(ReceiverRecord::value)
            .doOnNext(event -> 
                log.info("Received event: {}", event)
            );
    }
} 