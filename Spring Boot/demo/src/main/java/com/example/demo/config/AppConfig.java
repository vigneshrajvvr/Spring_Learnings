package com.example.demo.config;

import com.example.demo.json.Greeting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {


    @Bean //@Scope("prototype")
    // @Lazy(value = false)
    public Greeting defaultGreeting() {
        return new Greeting();
    }

    @Bean
    public Greeting whatUpGreeting() {
        return new Greeting("What's up");
    }

    // in subclass, check to see if there is already
    // a bean called defaultGreeting in app context
    // if so, return it
    // is not, call super, then add it to app context and return it

    // ApplicationContext is a descendent of BeanFactory
    // ApplicationContet is eager
    // BeanFactory is lazy

}
