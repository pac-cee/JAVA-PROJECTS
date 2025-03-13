package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.PaymentStatus;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final OrderService orderService;
    private final EmailService emailService;

    @Value("${stripe.api-key}")
    private String stripeApiKey;

    public PaymentIntent createPaymentIntent(Order order) {
        try {
            Stripe.apiKey = stripeApiKey;

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(order.getTotalAmount().multiply(new BigDecimal(100)).longValue())
                .setCurrency("usd")
                .setDescription("Order #" + order.getOrderNumber())
                .setMetadata(Map.of("orderId", order.getId().toString()))
                .build();

            PaymentIntent intent = PaymentIntent.create(params);
            
            // Update order with payment intent ID
            order.setPaymentId(intent.getId());
            orderService.updateOrder(order);

            return intent;
        } catch (Exception e) {
            throw new PaymentProcessingException("Error creating payment intent", e);
        }
    }

    public void handlePaymentSuccess(String paymentIntentId) {
        Order order = orderService.findByPaymentId(paymentIntentId);
        order.setPaymentStatus(PaymentStatus.COMPLETED);
        orderService.updateOrder(order);
        
        // Send confirmation email
        emailService.sendOrderConfirmation(order);
    }
} 