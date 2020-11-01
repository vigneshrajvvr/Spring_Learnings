package com.practice.vvr.security.introduction.Introduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class IntroductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroductionApplication.class, args);
	}

}

@Component
class Bar {
	
	// dependency injection
	private final Foo foo;
	
	public Bar(Foo foo) {
		this.foo = foo;
	}
	
}

@Component
class Foo {
	
}
