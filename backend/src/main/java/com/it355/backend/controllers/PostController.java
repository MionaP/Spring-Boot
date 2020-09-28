package com.it355.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it355.backend.entities.Post;
import com.it355.backend.entities.User;
import com.it355.backend.repositories.PostRepository;
import com.it355.backend.repositories.PostRepositoryImpl;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class PostController {

	@Autowired
	private PostRepositoryImpl postRepositoryImpl;
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/getPostsById")
	public List<Post> getPostssById(@RequestParam("user_id") int user_id) {
		return postRepositoryImpl.getPostsById(user_id);
	}

	@GetMapping("/getCurrentUsersPost")
	public List<Post> getCurrentUsersPost(@RequestParam("email") String email) {
		return postRepositoryImpl.getCurrentUsersPost(email);
	}

	@GetMapping("/getPostFromFollowed")
	public List<Post> getPostFromFollowed(@RequestParam("user_id") int user_id) {
		return postRepositoryImpl.getPostFromFollowed(user_id);
	}

	@PostMapping("/uploadPost")
	public Post uploadPost(@RequestBody Post post) {
		User user = new User();
		user.setUser_id(post.getUser_id());
		post.setUser(user);
		return postRepository.save(post);
	}

	@GetMapping("getPostById")
	public Post getPostById(@RequestParam("post_id") int post_id) {
		return postRepository.findOne(post_id);
	}

}
