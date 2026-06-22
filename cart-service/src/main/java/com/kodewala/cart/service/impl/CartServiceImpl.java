package com.kodewala.cart.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodewala.cart.dto.ProductDto;
import com.kodewala.cart.entity.Cart;
import com.kodewala.cart.feign.ProductClient;
import com.kodewala.cart.repository.CartRepository;
import com.kodewala.cart.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger log =
            LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartRepository repository;

    @Autowired
    private ProductClient productClient;

    @Override
    public Cart addToCart(Cart cart) {

        log.info("Add to cart process started for productId: {}",
                cart.getProductId());

        ProductDto product =
                productClient.getProduct(
                        cart.getProductId());

        log.info("Product details received from Product Service: {}",
                product);

        if (product == null) {

            log.error("Product not found with id: {}",
                    cart.getProductId());

            throw new RuntimeException(
                    "Product Not Found");
        }

        Cart savedCart = repository.save(cart);

        log.info("Product added to cart successfully. Cart Id: {}",
                savedCart.getId());

        return savedCart;
    }

    @Override
    public List<Cart> getAll() {

        log.info("Fetching all cart items");

        List<Cart> carts = repository.findAll();

        log.info("Total cart items found: {}",
                carts.size());

        return carts;
    }
}