package com.practice.vvr.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practice.vvr.controller.exception.UserNotFoundException;
import com.practice.vvr.model.Post;
import com.practice.vvr.model.User;
import com.practice.vvr.repository.PostRepository;
import com.practice.vvr.repository.UserRepository;

@RestController
public class UserJpaController {

	private final Logger USER_LOGGER = Logger.getLogger(UserJpaController.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	// GET /users
	// retrieveAllUsers
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	// GET /users/{id}
	// retrieveUser(int id)
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable("id") int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Id-" + id);
		}

		// To add additional data related to the model
		EntityModel<User> model = EntityModel.of(user.get());

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		model.add(linkTo.withRel("all-users"));

		return model;
	}

	// input - details of user
	// output - Created response & return the created uri
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		USER_LOGGER.info(location.getPath());

		return ResponseEntity.created(location).build();
	}

	/*
	 * When deleting a user, we can return 200 Ok status (return type as void) or no
	 * content status -> return a response entity with no content status
	 */
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable("id") int userId) {
		userRepository.deleteById(userId);
	}

	// GET /users
	// retrieveAllPosts
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable("id") int id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("Id-" + id);
		}

		return user.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable("id") int id, @Valid @RequestBody Post post) {

		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("Id-" + id);
		}
		
		post.setUser(user.get());
		
		postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		USER_LOGGER.info(location.getPath());

		return ResponseEntity.created(location).build();
	}

}
