package com.example.btcpricetracker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletionStage;

@Service
public class BinancePriceService {
    private static final String BINANCE_WS_URL = "wss://stream.binance.com:9443/ws/btcusdt@trade";

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BinancePriceService() {
        start();
    }
    private void start() {
        HttpClient client = HttpClient.newHttpClient();
        client.newWebSocketBuilder().buildAsync(URI.create(BINANCE_WS_URL), new WebSocket.Listener() {
            @Override
            public void onOpen(WebSocket webSocket) {
                webSocket.request(1);
            }

            @Override
            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                try {
                    JsonNode jsonNode = objectMapper.readTree(data.toString());
                    double price = jsonNode.get("p").asDouble();
                    messagingTemplate.convertAndSend("/topic/btc-price", price);
                } catch (Exception e) {
                    // log or handle error
                }
                webSocket.request(1);
                return null;
            }

            @Override
            public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
                webSocket.request(1);
                return null;
            }
        });
    }
}
