package com.shivraj.blog.bloggingapplicationapis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivraj.blog.bloggingapplicationapis.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String Email);
}
