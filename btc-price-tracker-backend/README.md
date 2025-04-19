# BTC Price Tracker Backend

This is a Spring Boot backend that streams real-time BTC/USDT price updates from Binance to frontend clients using WebSockets.

## How it works
- Connects to Binance WebSocket for BTC/USDT trades
- Broadcasts the latest price to all connected frontend clients via `/ws-btc` endpoint (STOMP over SockJS)
- Frontend can subscribe to `/topic/btc-price` for real-time updates

## Running the backend

1. Make sure you have Java 17+ and Maven installed.
2. In this directory, run:

```
mvn spring-boot:run
```

The backend will start on port 8081 by default.

## WebSocket Endpoint
- **Endpoint:** `/ws-btc` (SockJS/STOMP)
- **Topic:** `/topic/btc-price`

## Example Frontend Usage
Use a STOMP client (e.g., `@stomp/stompjs` in React) to connect and subscribe to `/topic/btc-price`.
