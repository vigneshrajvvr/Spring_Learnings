package com.practice.vvr.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.vvr.springboot.service.PaymentService;
import com.practice.vvr.springboot.service.PaymentServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoreApplicationTests {
	
	@Autowired
	PaymentService service;
	
	@Autowired
	PaymentServiceImpl serviceImpl;

	@Test
	void testDependencyInjection() {
		assertNotNull(service);
		assertNotNull(serviceImpl.getDao());
		
		
	}

}
