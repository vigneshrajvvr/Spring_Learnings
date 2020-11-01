package com.practice.vvr.security.introduction.Introduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

// Dependency Injection
 
@SpringBootApplication
public class IntroductionApplication {
	
	// Manually specifying creation of objects
//	@Bean
//	Foo foo() {
//		return new Foo();
//	}
	
	@Bean
	Bar bar(Foo foo) {
		return new Bar(foo);
	}
	

	public static void main(String[] args) {	
		SpringApplication.run(IntroductionApplication.class, args);
	}

}

//@Component
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
