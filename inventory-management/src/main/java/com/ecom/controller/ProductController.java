package com.ecom.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.ProductRequest;
import com.ecom.entity.Product;
import com.ecom.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping
	public Product add(@RequestBody ProductRequest request) {
		return service.addProduct(request);
	}

//	@GetMapping
//	public List<Product> getAll() {
//		return service.getAllProducts();
//	}

//	@GetMapping
//
//	public Page<Product> getAllProducts(
//
//	@RequestParam(defaultValue = "0") int page,
//
//	@RequestParam(defaultValue = "5") int size
//
//	){
//
//	    return service.getAllProducts(page,size);
//
//	}
	
	@GetMapping
	public Page<Product> getProducts(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id") String sortBy) {

	    return service.getProducts(page, size, sortBy);
	}
	
	@GetMapping("/{id}")
	public Product get(@PathVariable Long id) {
		return service.getProduct(id);
	}

	@PutMapping("/{id}")
	public Product update(@PathVariable Long id, @RequestBody ProductRequest request) {

		return service.updateProduct(id, request);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {

		service.deleteProduct(id);

		return "Deleted Successfully";
	}

	@PatchMapping("/{id}/stock")

	public Product updateStock(@PathVariable Long id, @RequestParam Integer quantity) {

		return service.updateStock(id, quantity);
	}

	@GetMapping("/search")

	public List<Product> search(@RequestParam String keyword) {

		return service.searchProduct(keyword);
	}

}
