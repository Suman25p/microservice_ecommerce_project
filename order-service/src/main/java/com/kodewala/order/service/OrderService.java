package com.kodewala.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodewala.order.entity.Order;

@Service
public interface OrderService {

    Order placeOrder(Order order);

    List<Order> getOrders();
}