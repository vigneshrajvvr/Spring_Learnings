package com.practice.vvr.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * /myAccount - Secured /myBalance - Secured /myLoans - Secured /myCards -
	 * Secured /notices - Not secured /contact - Not secured
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * Default configuration
		 * 
		 * http .authorizeRequests() .anyRequest().authenticated(); http.formLogin();
		 * http.formBasic();
		 */

		/*
		 * Custom configuration as per our requirement
		 */ 
		 
		  http .authorizeRequests() .antMatchers("/myAccount").authenticated()
		  .antMatchers("/myBalance").authenticated()
		  .antMatchers("/myLoans").authenticated()
		  .antMatchers("/myCards").authenticated() .antMatchers("/notices").permitAll()
		  .antMatchers("/contact").permitAll(); http.formLogin(); http.httpBasic();
		 
		
		/*
		 * formLogin() - Request from browser login form httpBasic() - Request from
		 * other APIs
		 */

		/*
		 * Configuration to deny all the requests
		 *
		 * http
		 * 	 .authorizeRequests().anyRequest().denyAll();
		 * http.formLogin();
		 * http.httpBasic();
		 */
		
		/*
		 * Configuration to permit all the requests
		 *
		
		 * http
		 * 	 .authorizeRequests().anyRequest().permitAll();
		 * http.formLogin();
		 * http.httpBasic();
		 */
		 
	}
	
	/*
	 * This method should be overridden to provide custom authentication
	 * It will not accept user which are defined in the application.properties file
	 * 
	 */
	
	
	/*
	 * In memory authentication - to have users within the application
	 */
	
	/* @Override
	 * protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 *		auth.inMemoryAuthentication().withUser("admin").password("12345").authorities("admin").and()
	 *			.withUser("user").password("12345").authorities("read").and()
	 *			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	 *	}
	 */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		UserDetails user1 = User.withUsername("admin").password("12345").authorities("admin").build();
		UserDetails user2 = User.withUsername("user").password("12345").authorities("admin").build();
		userDetailsService.createUser(user1);
		userDetailsService.createUser(user2);
		
		auth.userDetailsService(userDetailsService);

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
