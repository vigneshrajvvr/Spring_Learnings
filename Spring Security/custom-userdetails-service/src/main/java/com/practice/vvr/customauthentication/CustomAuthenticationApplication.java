package com.practice.vvr.customauthentication;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CustomAuthenticationApplication {
	
	@Bean 
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CustomUserDetailsService customUserDetailsService() {
		
		Collection<UserDetails> users = Arrays.asList(
				new CustomUserDetails("jlong", new BCryptPasswordEncoder().encode("password"), true, "USER"),
				new CustomUserDetails("ROB", new BCryptPasswordEncoder().encode("password"), true, "USER", "ADMIN")
				);
		
		return new CustomUserDetailsService(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomAuthenticationApplication.class, args);
	}

}

@RestController
class GreetingRestController {
	
	@GetMapping("/greeting")
	String greet(Principal p) {
		return "Greetings " + p.getName() + "!";
	}
	
}

@Configuration
@EnableWebSecurity
class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic();
		
		http.authorizeRequests().anyRequest().authenticated();
	
	}
	
}

class CustomUserDetailsService implements UserDetailsService {
	
	private final Map<String, UserDetails> users= new ConcurrentHashMap<>();
	
	public CustomUserDetailsService(Collection<UserDetails> seedUsers) {
		seedUsers.forEach(user -> this.users.put(user.getUsername(), user));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(this.users.containsKey(username)) {
			return this.users.get(username);
		}
		
		throw new UsernameNotFoundException("Couldn't find " + username);
	}
	
}

class CustomUserDetails implements UserDetails {
	
	private Set<GrantedAuthority> authorities = new HashSet<>();
	private final String username, password;
	private final boolean active;
	
	public CustomUserDetails(String username, String password, boolean active, String ... authorities) {
		this.username = username;
		this.password = password;
		this.active = active;
		this.authorities = Stream.of(authorities).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
				
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.active;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.active;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}
	
}
