package com.it355.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it355.backend.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>, CommentCustomRepository<Comment, Integer> {

}
