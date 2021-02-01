package com.practice.vvr.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.vvr.springboot.assingment1.Calculations;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Assignment1ApplicationTests {

	@Autowired
	private Calculations testCalculation;
	
	@Test
	void sumTest() {
		assertEquals(15, testCalculation.sumofTwoNumber(5,10));
	}

}
