package com.example.demo;

import com.example.demo.json.Greeting;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired @Qualifier("defaultGreeting")
	private Greeting greeting;

	@Autowired
	private ApplicationContext context;

	void autoWiringWorked() {
		assertNotNull(context);
	}

	@Test
	void contextLoads() {
		int count = context.getBeanDefinitionCount();
		System.out.println("There are "+count+"beans in the application context");

		for(String name: context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

	@Test
	void verfiyHelloControllerIsInAppContext() {
		List<String> beans = Arrays.stream(context.getBeanDefinitionNames())
				.collect(Collectors.toList());
		assertTrue(beans.contains("helloController"));
	}

	@Test
	void verifyDefaultGreetingInAppContext() {
		Greeting greeting = context.getBean("defaultGreeting", Greeting.class);
		assertNotNull(greeting);
	}

	@Test
	void checkSingletonBehavior() {
		Greeting greeting1 = context.getBean("defaultGreeting", Greeting.class);
		Greeting greeting2 = context.getBean("defaultGreeting", Greeting.class);

		//both instances assigned to same instance
		//By default, Spring manages singleton
		assertSame(greeting1, greeting2);
		System.out.println(greeting1.getMessage());
		greeting1.setMessage("Demo test for singleton");
		System.out.println(greeting2.getMessage());

	}

}
