package com.kodewala.cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodewala.cart.entity.Cart;
@Service
public interface CartService {
	Cart addToCart(Cart cart);

    List<Cart> getAll();
}
