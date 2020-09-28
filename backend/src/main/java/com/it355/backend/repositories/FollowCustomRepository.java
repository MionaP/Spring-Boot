package com.it355.backend.repositories;

import java.io.Serializable;
import java.util.List;

import com.it355.backend.entities.*;

public interface FollowCustomRepository<T, ID extends Serializable> {

	public Follow getFollowing(String email);

	public Follow getFollowed(String email);

	public Follow checkIfFollow(int user_id, int selected_id);

}
