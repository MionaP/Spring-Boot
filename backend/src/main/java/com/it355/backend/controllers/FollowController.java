package com.it355.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it355.backend.entities.Follow;
import com.it355.backend.entities.Post;
import com.it355.backend.entities.User;
import com.it355.backend.repositories.FollowRepository;
import com.it355.backend.repositories.FollowRepositoryImpl;

@RestController
@RequestMapping("/follow")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class FollowController {

	@Autowired
	private FollowRepositoryImpl followRepositoryImpl;
	@Autowired
	private FollowRepository followRepository;

	@GetMapping("/getFollowing")
	public Follow geFollow(@RequestParam("email") String email) {
		return followRepositoryImpl.getFollowing(email);
	}

	@GetMapping("/getFollowed")
	public Follow geFollowed(@RequestParam("email") String email) {
		return followRepositoryImpl.getFollowed(email);
	}

	@GetMapping("/checkIfFollow")
	public Follow checkIfFollow(@RequestParam("user_id") int user_id, @RequestParam("selected_id") int selected_id) {
		return followRepositoryImpl.checkIfFollow(user_id, selected_id);
	}

	@DeleteMapping("/deleteFollow")
	public void deleteFollow(@RequestParam("user_id") int user_id, @RequestParam("selected_id") int selected_id) {
		List<Follow> list = followRepository.findAll();
		for (Follow follow : list) {
			if (follow.getUser_id() == user_id && follow.getFollowed() == selected_id) {
				followRepository.delete(follow);
			}
		}

	}

	@PostMapping("/startFollow")
	public Follow startFollow(@RequestBody Follow follow) {
		System.out.println(follow.getFollowed() + " followed");
		System.out.println(follow.getUser_id() + " user id");
		User user = new User();
		user.setUser_id(follow.getUser_id());
		follow.setUser(user);
		return followRepository.save(follow);
	}

}
