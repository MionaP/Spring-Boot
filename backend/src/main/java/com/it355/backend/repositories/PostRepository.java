package com.it355.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it355.backend.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer>, PostCustomRepository<Post, Integer> {

}
