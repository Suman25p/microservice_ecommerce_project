package com.ecom.service;

import java.util.List;

import com.ecom.dto.ProductRequest;
import com.ecom.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
	Product addProduct(ProductRequest request);

	Product updateProduct(Long id, ProductRequest request);

	void deleteProduct(Long id);

	Product getProduct(Long id);

	//List<Product> getAllProducts();
	Page<Product> getProducts(int page, int size, String sortBy);

	Product updateStock(Long id, Integer quantity);

	List<Product> searchProduct(String keyword);
}
