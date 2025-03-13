package com.example.ecommerce.controller;

import com.example.ecommerce.service.PaymentService;
import com.stripe.model.PaymentIntent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "Payment management APIs")
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(
        summary = "Create payment intent",
        description = "Creates a payment intent for an order using Stripe"
    )
    @PostMapping("/create-payment-intent/{orderId}")
    public ResponseEntity<PaymentIntent> createPaymentIntent(@PathVariable Long orderId) {
        Order order = orderService.findById(orderId);
        PaymentIntent intent = paymentService.createPaymentIntent(order);
        return ResponseEntity.ok(intent);
    }

    @Operation(
        summary = "Handle webhook",
        description = "Handles Stripe webhook events for payment status updates"
    )
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload) {
        try {
            Event event = Event.fromJson(payload);
            if ("payment_intent.succeeded".equals(event.getType())) {
                PaymentIntent intent = (PaymentIntent) event.getData().getObject();
                paymentService.handlePaymentSuccess(intent.getId());
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Webhook error");
        }
    }
} 