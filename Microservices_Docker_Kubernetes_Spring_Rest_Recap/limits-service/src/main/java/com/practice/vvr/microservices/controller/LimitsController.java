package com.practice.vvr.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.microservices.configuration.Configuration;
import com.practice.vvr.microservices.controller.bean.Limits;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/limits") 
	public Limits retrieveLimits() {
//		return new Limits(1,1000); 
		return new Limits(configuration.getMinimum(), configuration.getMaximum());
	}
	
}
