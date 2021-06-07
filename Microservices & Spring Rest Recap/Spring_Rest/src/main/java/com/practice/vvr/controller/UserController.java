package com.practice.vvr.controller;

import java.net.URI;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practice.vvr.controller.exception.UserNotFoundException;
import com.practice.vvr.model.User;
import com.practice.vvr.repository.UserDaoService;

@RestController
public class UserController {
	
	private final Logger USER_LOGGER = Logger.getLogger(UserController.class);
		
	@Autowired
	private UserDaoService userDaoService;
	
	//GET /users
	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}
	
	//GET /users/{id}
	//retrieveUser(int id)
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable("id") int id) {
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("Id-"+id);
		}
		return user;
	}
	
	//input - details of user
	//output - Created response & return the created uri
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userDaoService.save(user);
				
		URI location = ServletUriComponentsBuilder
									.fromCurrentRequest()
									.path("/{id}")
									.buildAndExpand(savedUser.getId())
									.toUri();
		
		USER_LOGGER.info(location.getPath());
		
		return ResponseEntity.created(location).build();
	}
	
}
