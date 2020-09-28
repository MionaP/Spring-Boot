package com.it355.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it355.backend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserCustomRepository<User, Integer> {

}
