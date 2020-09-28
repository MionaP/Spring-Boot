package com.it355.backend.repositories;

import java.io.Serializable;
import java.util.List;

import com.it355.backend.entities.Post;

public interface PostCustomRepository<T, ID extends Serializable> {

	public List<Post> getPostsById(int id);

	public List<Post> getCurrentUsersPost(String email);

	public List<Post> getPostFromFollowed(int user_id);

}