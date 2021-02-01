package com.practice.vvr.springboot.assingment1;

import org.springframework.stereotype.Component;

@Component
public class CalculationsImpl implements Calculations {

	@Override
	public int sumofTwoNumber(int a, int b) {
		return a + b;
	}

}
