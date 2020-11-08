package com.practice.vvr.security.introduction.Introduction;

import java.util.UUID;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

// Dependency Injection
 
@SpringBootApplication
public class IntroductionApplication {
	
	// Manually specifying creation of objects
	//	@Bean
	//	Foo foo() {
	//		return new Foo();
	//	}
	
	// To create an object with uuid associated with it
	// Object of the UuidService class is being accessed by the component name "uuid"
	// @Value annotation binds the value with the parameter uuid
	//	@Bean
	//	Bar bar(Foo foo, @Value("#{uuid.buildUuid()}") String uuid) {
	//		return new Bar(foo, uuid);
	//	}
	

	public static void main(String[] args) {	
		SpringApplication.run(IntroductionApplication.class, args);
	}

}

//To provide uuid 
	@Component("uuid")
	class UuidService {
		
		public String buildUuid() {
			return UUID.randomUUID().toString();
		}
		
	}

@Component
class Bar {
	
	// dependency injection
	private final Foo foo;
	
	private final Log log = LogFactory.getLog(getClass());
	
	// Spring expression language - to evaluate expressions at runtimes
	public Bar(Foo foo, @Value("#{uuid.buildUuid()}") String uuid,
			@Value("#{ 2 > 1}") boolean proceed) {
		this.foo = foo;
		this.log.info("UUID " + uuid);
		this.log.info("Proceed " + proceed);
	}
	
}

@Component
class Foo {
	
}
