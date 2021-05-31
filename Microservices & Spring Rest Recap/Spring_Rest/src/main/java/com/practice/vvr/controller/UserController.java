package com.practice.vvr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.model.User;
import com.practice.vvr.repository.UserDaoService;

@RestController
public class UserController {
	
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
		return userDaoService.findOne(id);
	}
	
	//input - details of user
	//output - Created response & return the created uri
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		User savedUser = userDaoService.save(user);
	}
	
}
