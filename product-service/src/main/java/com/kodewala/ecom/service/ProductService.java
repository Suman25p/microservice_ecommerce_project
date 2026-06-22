package com.kodewala.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodewala.entity.Product;

@Service
public interface ProductService {
	Product save(Product product);

    List<Product> getAllProducts();

    Product getProduct(Long id);

    void deleteProduct(Long id);
}
