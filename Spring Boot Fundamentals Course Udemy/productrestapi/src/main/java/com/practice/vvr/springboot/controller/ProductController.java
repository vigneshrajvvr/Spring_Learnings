package com.practice.vvr.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.springboot.entity.Product;
import com.practice.vvr.springboot.repository.ProductRepository;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	private ProductRepository productRepository;
	
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@ApiOperation(value="Retrieves all the products",
				  notes="List all the products",
				  response=Product.class,
				  responseContainer="List",
				  produces="application/json")
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productRepository.findAll();
	}
	
	@GetMapping("/products/{id}")
	@Transactional(readOnly=true)
	@Cacheable("product-cache")
	public Product getProduct(@PathVariable("id") int id) {
		LOGGER.info("Finding product by id : "+id);
		return productRepository.findById(id).get();
	}
	
	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@DeleteMapping("/products/{id}")
	@CacheEvict("product-cache")
	public void deleteProduct(@PathVariable("id") int id) {
		 productRepository.deleteById(id);
	}

}
