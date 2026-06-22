package com.kodewala.ecom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kodewala.ecom.service.ProductService;
import com.kodewala.entity.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger log =
            LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;

    @PostMapping
    public Product save(@RequestBody Product product) {

        log.info("Product save request received: {}", product);

        Product savedProduct = service.save(product);

        log.info("Product saved successfully with id: {}", savedProduct.getId());

        return savedProduct;
    }

    @GetMapping
    public List<Product> getAll() {

        log.info("Fetching all products");

        List<Product> products = service.getAllProducts();

        log.info("Total products found: {}", products.size());

        return products;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {

        log.info("Fetching product with id: {}", id);

        Product product = service.getProduct(id);

        log.info("Product fetched successfully: {}", product);

        return product;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        log.info("Delete request received for product id: {}", id);

        service.deleteProduct(id);

        log.info("Product deleted successfully with id: {}", id);

        return "Deleted Successfully";
    }
}