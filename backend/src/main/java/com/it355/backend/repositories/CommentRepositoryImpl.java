package com.it355.backend.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.it355.backend.entities.Comment;

public class CommentRepositoryImpl implements CommentCustomRepository<Comment, Integer> {

	@Autowired
	private SessionFactory session;

}
