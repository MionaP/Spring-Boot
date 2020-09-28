package com.it355.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.it355.backend.repositories.UserRepository;
import com.it355.backend.repositories.UserRepositoryImpl;
import com.it355.backend.entities.User;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserRepositoryImpl userRepositoryImpl;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/allusers")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/getEmailAndPass")
	public List<User> getEmailAndPassword() {
		return userRepositoryImpl.getEmailAndPass();
	}

	@PostMapping("/insert")
	public User insert(@RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping("/getUser")
	public User getUser(@RequestParam("email") String email) {
		return userRepositoryImpl.getUser(email);
	}

	@GetMapping("/getUsersSearch")
	public List<User> getUsersSearch(@RequestParam("name") String name, @RequestParam("email") String email) {
		return userRepositoryImpl.getUsersSearch(name, email);
	}

	@GetMapping("/getUsersById")
	public User getUsersById(@RequestParam("id") int id) {
		return userRepositoryImpl.getUserById(id);
	}

	@PutMapping("/changeUser")
	public User updateUser(@RequestBody User user) {
		return userRepository.save(user);

	}

	@GetMapping("/getIdByEmail")
	public User getIdByEmail(@RequestParam("email") String email) {
		return userRepositoryImpl.getIdByEmail(email);
	}

	@GetMapping("/getProfileImgForPosts")
	public List<User> getProfileImgForPosts(@RequestParam("user_id") int user_id) {
		return userRepositoryImpl.getProfileImgForPosts(user_id);
	}

	@GetMapping("/getCommentators")
	public List<User> getCommentators() {
		return userRepositoryImpl.getCommentators();
	}

}
