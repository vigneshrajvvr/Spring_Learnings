package com.practice.vvr.springboot;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.practice.vvr.springboot.entity.Product;
import com.practice.vvr.springboot.repository.ProductRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductrestapiMockMvcTests {

	/*
	 * Unit testing
	 */
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Test
	public void testFindAll() throws Exception{
		Product product = new Product();
		product.setId(1);
		product.setName("Macbook");
		product.setDescription("It's awesome");
		List<Product> products = new ArrayList<>();
		products.add(product);
	    when(productRepository.findAll()).thenReturn(products);
	    
	    mockMvc.perform(get("/productsapi/products").contextPath("/productsapi")).andExpect(status().isOk());
	}
}



