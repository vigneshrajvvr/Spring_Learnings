package com.practice.vvr.microservices.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.microservices.entity.CurrencyExchange;

@RestController
public class CurrencyExchangeController {
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable("from") String from,
												  @PathVariable("to") String to) {
		return new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(65.00));
	}

}
