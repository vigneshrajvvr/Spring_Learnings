package com.practice.vvr.security.introduction;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


// spring.factories file contains all the configuration details
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
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	

	public static void main(String[] args) {	
		SpringApplication.run(IntroductionApplication.class, args);
	}

}

// Servlet filter
@Component
class LoggingFilter implements javax.servlet.Filter {
	
	private final Log log = LogFactory.getLog(getClass());

	// To intercept incoming http request
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Assert.isTrue(request instanceof HttpServletRequest,"This assumes you have an HTTP request");
		
		HttpServletRequest httpServletRequest = HttpServletRequest.class.cast(request);
		
		String uri = httpServletRequest.getRequestURI();
		
		this.log.info("New request for "+ uri);
		
		long time = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		long delta = System.currentTimeMillis() - time;
		
		this.log.info("Request for " + uri + " took " + delta + "ms");
		
	}
}

// Spring AOP - @Around 
@Component
@Aspect
class LoggingAspect {
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Around("execution( * com.practice.vvr..*.*(..) )")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		// Before
		this.log.info("Before " + pjp.toString());
		Object object = pjp.proceed();
		// After
		this.log.info("After " + pjp.toString());
		return object;
	}
	
}


// Rest controller to access google books api and get book details based on isbn 
@RestController
class IsbnRestController {
	
	private final RestTemplate restTemplate;
	
	IsbnRestController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	// localhost:8080/books/39929u22322
	@GetMapping("/books/{isbn}")
	String lookUpBookByIsbn(@PathVariable("isbn") String isbn) {
		
		ResponseEntity<String> exchange = this.restTemplate.exchange("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn,
				HttpMethod.GET, null, String.class);
		
		String body = exchange.getBody();
		
		return body;
		
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
