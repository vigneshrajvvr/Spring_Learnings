package com.practice.vvr.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.model.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messages;
	
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
	
	// Internationalization - i18n
	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized(
//			@RequestHeader(name="Accept-Language", required=false) Locale locale
			) {
		return messages.getMessage("good.morning.message", null, "Good Morning", LocaleContextHolder.getLocale());
	}
	
}
