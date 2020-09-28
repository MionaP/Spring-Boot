package com.it355.backend.repositories;

import java.io.Serializable;
import java.util.List;

import com.it355.backend.entities.User;

public interface UserCustomRepository<T, ID extends Serializable> {
	public List<User> getEmailAndPass();

	public User getUser(String email);

	public List<User> getUsersSearch(String name, String email);

	public User getUserById(int id);

	public User getIdByEmail(String email);
	
	public List<User> getProfileImgForPosts(int user_id);
	
	public List<User> getCommentators();
}
