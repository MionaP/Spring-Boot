package com.it355.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it355.backend.entities.Comment;
import com.it355.backend.entities.Post;
import com.it355.backend.entities.User;
import com.it355.backend.repositories.CommentRepository;
import com.it355.backend.repositories.CommentRepositoryImpl;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CommentRepositoryImpl commentRepositoryImpl;

	@GetMapping("/getComments")
	public List<Comment> getComments() {
		return commentRepository.findAll();
	}

	@PostMapping("/postComment")
	public Comment postComment(@RequestBody Comment comment) {
		User tempUser = new User();
		tempUser.setUser_id(comment.getUser_id());
		Post tempPost = new Post();
		tempPost.setPost_id(comment.getPost_id());

		comment.setPost(tempPost);
		comment.setUser(tempUser);

		return commentRepository.save(comment);

	}
}
