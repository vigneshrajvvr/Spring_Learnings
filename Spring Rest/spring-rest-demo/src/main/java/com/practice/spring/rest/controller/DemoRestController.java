package com.practice.spring.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {
	
	//code for /hello endpoint
	
	@GetMapping("/hello")
	public String getHello() {
		return "Hello World!";
	}

}
