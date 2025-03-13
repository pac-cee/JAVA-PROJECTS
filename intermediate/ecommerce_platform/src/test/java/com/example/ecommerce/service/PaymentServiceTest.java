package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.PaymentStatus;
import com.stripe.model.PaymentIntent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @Mock
    private OrderService orderService;
    
    @Mock
    private EmailService emailService;

    @InjectMocks
    private PaymentService paymentService;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setOrderNumber("TEST-001");
        testOrder.setTotalAmount(new BigDecimal("99.99"));
    }

    @Test
    void createPaymentIntent_Success() {
        PaymentIntent intent = paymentService.createPaymentIntent(testOrder);
        
        assertNotNull(intent);
        assertEquals(9999L, intent.getAmount());
        verify(orderService).updateOrder(any(Order.class));
    }

    @Test
    void handlePaymentSuccess_UpdatesOrderAndSendsEmail() {
        when(orderService.findByPaymentId(anyString())).thenReturn(testOrder);

        paymentService.handlePaymentSuccess("pi_123");

        assertEquals(PaymentStatus.COMPLETED, testOrder.getPaymentStatus());
        verify(orderService).updateOrder(testOrder);
        verify(emailService).sendOrderConfirmation(testOrder);
    }
} 