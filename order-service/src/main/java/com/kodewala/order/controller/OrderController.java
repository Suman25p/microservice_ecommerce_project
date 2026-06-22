package com.kodewala.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kodewala.order.entity.Order;
import com.kodewala.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger log =
            LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService service;

    @PostMapping
    public Order create(@RequestBody Order order) {

        log.info("Order creation request received: {}", order);

        Order savedOrder = service.placeOrder(order);

        log.info("Order created successfully with id: {}",
                savedOrder.getId());

        return savedOrder;
    }

    @GetMapping
    public List<Order> getAll() {

        log.info("Fetching all orders");

        List<Order> orders = service.getOrders();

        log.info("Total orders found: {}", orders.size());

        return orders;
    }
}