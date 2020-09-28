package com.it355.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it355.backend.entities.Follow;

public interface FollowRepository extends JpaRepository<Follow, Integer>, FollowCustomRepository<Follow, Integer> {

}
