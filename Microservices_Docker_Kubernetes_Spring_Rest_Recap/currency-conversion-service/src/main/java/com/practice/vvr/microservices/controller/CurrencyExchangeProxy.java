package com.practice.vvr.microservices.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.practice.vvr.microservices.entity.CurrencyConversion;

@FeignClient(name="currency-exchange", url="localhost:8000")
public interface CurrencyExchangeProxy { 
	
	@GetMapping("currency-exchange/from/{from}/to/{to}") 
	public CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from,
												  @PathVariable("to") String to);

}
