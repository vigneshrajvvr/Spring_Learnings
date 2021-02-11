package com.practice.vvr.springboot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.practice.vvr.springboot.entity.Product;

@SpringBootTest
class ProductrestapiApplicationTests {

	@Test
	void testGetProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject("http://localhost:8081/productsapi/products/1", Product.class);
		assertNotNull(product);
		assertEquals("IPhone", product.getName());
	}
	
	@Test
	void testCreateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = new Product();
		product.setName("Samsung Mobile");
		product.setDescription("It's Awesome");
		product.setPrice(1000);
		Product newProduct = restTemplate.postForObject("http://localhost:8081/productsapi/products", product, Product.class);
		assertNotNull(newProduct);
		assertNotNull(newProduct.getId());
		assertEquals("Samsung Mobile", newProduct.getName());
	}
	
	@Test
	void testUpadateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject("http://localhost:8081/productsapi/products/1", Product.class);
		product.setPrice(2000);
		restTemplate.put("http://localhost:8081/productsapi/products/", product);
		
	}

}
