package com.practice.vvr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.model.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	// GET
	// URI - /hello-world
	// @RequestMapping(method = RequestMethod.GET, path="/hello-world")
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World!!";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World!!");
	}

	@GetMapping("/hello-world-bean/{beanName}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable("beanName") String beanName) {
		return new HelloWorldBean("Hello World!! " + beanName);
	}
	
}
