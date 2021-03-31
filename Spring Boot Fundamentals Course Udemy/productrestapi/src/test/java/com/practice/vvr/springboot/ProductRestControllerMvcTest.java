package com.practice.vvr.springboot;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.practice.vvr.springboot.entity.Product;
import com.practice.vvr.springboot.repository.ProductRepository;

@SpringBootTest
public class ProductRestControllerMvcTest {
	
	/*
	 * Unit testing
	 */
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Test
	public void testFindAll() {
		Product product = new Product();
		product.setId(1);
		product.setName("Macbook");
		product.setDescription("It's awesome");
		List<Product> products = new ArrayList<>();
		products.add(product);
	    when(productRepository.findAll()).thenReturn(products);
	}

}
