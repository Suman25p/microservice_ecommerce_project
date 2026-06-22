package com.kodewala.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kodewala.cart.entity.Cart;
import com.kodewala.cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private static final Logger log =
            LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService service;

    @PostMapping("/add")
    public Cart add(@RequestBody Cart cart) {

        log.info("Add to cart request received: {}", cart);

        Cart savedCart = service.addToCart(cart);

        log.info("Product added to cart successfully with id: {}",
                savedCart.getId());

        return savedCart;
    }

    @GetMapping
    public List<Cart> getAll() {

        log.info("Fetching all cart items");

        List<Cart> carts = service.getAll();

        log.info("Total cart items found: {}", carts.size());

        return carts;
    }
}