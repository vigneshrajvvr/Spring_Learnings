package com.practice.vvr.microservices.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.microservices.entity.CurrencyConversion;

@RestController
public class CurrencyConversionController {

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
														  @PathVariable BigDecimal quantity) {
		
		return new CurrencyConversion(1000L, "USD", "INR", quantity, BigDecimal.ONE, BigDecimal.ONE, "");
		
	}

}
