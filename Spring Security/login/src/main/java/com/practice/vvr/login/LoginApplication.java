package com.practice.vvr.login;

import java.security.Principal;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	// In memory authentication
	@Bean
	UserDetailsManager inMemoryAuthentication() {
		return new InMemoryUserDetailsManager(); 
	}

	// Creation of users.
	// For bean implemented InitializingBean, it will run afterPropertiesSet() after
	// all bean properties have been set.
	@Bean
	InitializingBean initializer(UserDetailsManager manager) {
		return () -> {

			UserDetails josh = User.withDefaultPasswordEncoder().username("jlong").password("password").roles("USER")
					.build();
			manager.createUser(josh);

			UserDetails rob = User.withUserDetails(josh).username("rob").build();
			manager.createUser(rob);
		};
	}
}

@ControllerAdvice
class PrincipalControllerAdvice {
	
	@ModelAttribute("currentUser")  
	Principal principal(Principal p) { 
		return p;  
	}
	
} 

@Controller
class LoginController { 
	
	@GetMapping("/") 
	public String hidden(Model model) {
		return "hidden"; 
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout-success") 
	public String logout() {
		return "logout";
	}
	
}


@EnableWebSecurity
@Configuration
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// http authentication and authorization configuration
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().anyRequest().authenticated();
		
		http.formLogin().loginPage("/login").permitAll();
		
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/logout-success");

	}

}
