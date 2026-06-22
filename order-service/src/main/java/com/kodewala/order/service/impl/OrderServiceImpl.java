package com.kodewala.order.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodewala.order.dto.OrderEvent;
import com.kodewala.order.dto.ProductDto;
import com.kodewala.order.entity.Order;
import com.kodewala.order.feign.PaymentClient;
import com.kodewala.order.feign.ProductClient;
import com.kodewala.order.kafka.OrderProducer;
import com.kodewala.order.repository.OrderRepository;
import com.kodewala.order.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log =
            LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private OrderProducer orderProducer;

    @Override
    public Order placeOrder(Order order) {

        log.info(
                "Starting order placement for productId: {} and quantity: {}",
                order.getProductId(),
                order.getQuantity());

        ProductDto product =
                productClient.getProduct(
                        order.getProductId());

        log.info(
                "Received product details from Product Service: {}",
                product);

        if (product == null) {

            log.error(
                    "Product not found with id: {}",
                    order.getProductId());

            throw new RuntimeException(
                    "Product Not Found");
        }

        double total =
                product.getPrice()
                        * order.getQuantity();

        order.setTotalAmount(total);

        log.info(
                "Calculated total amount: {}",
                total);

        order.setStatus("CREATED");

        Order savedOrder =
                repository.save(order);

        log.info(
                "Order saved successfully with id: {} and status: {}",
                savedOrder.getId(),
                savedOrder.getStatus());

        // Payment Service Call with Circuit Breaker
        String paymentResponse =
                callPaymentService(
                        savedOrder.getId());

        log.info(
                "Payment Response : {}",
                paymentResponse);

        // Kafka Event
        OrderEvent event =
                new OrderEvent(
                        savedOrder.getId(),
                        savedOrder.getProductId(),
                        savedOrder.getQuantity(),
                        savedOrder.getStatus());

        try {

            orderProducer.publish(event);

        } catch (Exception e) {

            log.error(
                    "Kafka publish failed : {}",
                    e.getMessage());
        }

        return savedOrder;
    }

    @CircuitBreaker(
            name = "paymentService",
            fallbackMethod = "paymentFallback")
    public String callPaymentService(
            Long orderId) {

        return paymentClient.payOrder(
                orderId);
    }

    public String paymentFallback(
            Long orderId,
            Exception ex) {

        log.error(
                "Payment Service unavailable : {}",
                ex.getMessage());

        return "PAYMENT_PENDING";
    }

    @Override
    public List<Order> getOrders() {

        log.info(
                "Fetching all orders");

        List<Order> orders =
                repository.findAll();

        log.info(
                "Total orders found: {}",
                orders.size());

        return orders;
    }
}