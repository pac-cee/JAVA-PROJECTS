package com.example.trading.core;

import com.example.trading.model.*;
import com.example.trading.matching.*;
import com.example.trading.risk.*;
import com.lmax.disruptor.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.util.concurrent.*;
import java.time.*;

/**
 * High-Performance Trading Engine
 * Features:
 * - Ultra-low latency order matching
 * - Real-time risk management
 * - Multi-asset support
 * - Market data integration
 * - Performance monitoring
 */
@Component
public class TradingEngine {
    private final OrderBook orderBook;
    private final MatchingEngine matchingEngine;
    private final RiskManager riskManager;
    private final DisruptorQueue<Order> orderQueue;
    private final MetricsCollector metrics;
    
    // Thread pools for different operations
    private final ExecutorService matchingThreadPool;
    private final ScheduledExecutorService scheduledTasks;
    
    public TradingEngine(TradingConfig config) {
        this.orderBook = new OrderBook();
        this.matchingEngine = new MatchingEngine(config.getMatchingConfig());
        this.riskManager = new RiskManager(config.getRiskConfig());
        this.orderQueue = new DisruptorQueue<>(config.getQueueSize());
        this.metrics = new MetricsCollector();
        
        // Initialize thread pools
        this.matchingThreadPool = Executors.newFixedThreadPool(
            config.getMatchingThreads(),
            new NamedThreadFactory("matching-")
        );
        
        this.scheduledTasks = Executors.newScheduledThreadPool(2);
        
        // Start processing
        initializeProcessing();
    }
    
    private void initializeProcessing() {
        // Set up order processing pipeline
        orderQueue.handleEventsWithWorkerPool(matchingThreadPool,
            this::processOrder,
            this::handleError
        );
        
        // Schedule periodic tasks
        scheduledTasks.scheduleAtFixedRate(
            this::performHouseKeeping,
            1, 1, TimeUnit.MINUTES
        );
    }
    
    public CompletableFuture<OrderResult> submitOrder(Order order) {
        long startTime = System.nanoTime();
        
        return CompletableFuture.supplyAsync(() -> {
            // Validate order
            riskManager.validateOrder(order);
            
            // Submit to matching engine
            OrderResult result = matchingEngine.match(order);
            
            // Update order book
            orderBook.update(result);
            
            // Record metrics
            metrics.recordOrderProcessing(
                System.nanoTime() - startTime
            );
            
            return result;
        }, matchingThreadPool);
    }
    
    private void processOrder(Order order) {
        try {
            // Process the order through the matching engine
            OrderResult result = matchingEngine.match(order);
            
            // Update order book
            orderBook.update(result);
            
            // Notify participants
            notifyParticipants(result);
            
        } catch (Exception e) {
            handleError(e, order);
        }
    }
    
    private void handleError(Throwable error, Order order) {
        // Log error and notify admin
        metrics.recordError(error);
        // Implement error handling strategy
    }
    
    private void performHouseKeeping() {
        // Clean up expired orders
        orderBook.removeExpiredOrders();
        
        // Generate reports
        metrics.generatePerformanceReport();
    }
    
    public void shutdown() {
        matchingThreadPool.shutdown();
        scheduledTasks.shutdown();
        try {
            matchingThreadPool.awaitTermination(5, TimeUnit.SECONDS);
            scheduledTasks.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 