package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.dto.ProductRequest;
import com.ecom.entity.Product;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product addProduct(ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return repository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductRequest request) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));

        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return repository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));

        repository.delete(product);
    }

    @Override
    public Product getProduct(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));
    }

//    @Override
//    public List<Product> getAllProducts() {
//        return repository.findAll();
//    }
//    @Override
//    public Page<Product> getAllProducts(int page, int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        return repository.findAll(pageable);
//
//    }
    
    @Override
    public Page<Product> getProducts(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy));

        return repository.findAll(pageable);
    }

    @Override
    public Product updateStock(Long id, Integer quantity) {

        Product product = getProduct(id);

        product.setQuantity(quantity);

        return repository.save(product);
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

}
