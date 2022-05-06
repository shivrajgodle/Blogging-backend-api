package com.shivraj.blog.bloggingapplicationapis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivraj.blog.bloggingapplicationapis.entities.Category;
import com.shivraj.blog.bloggingapplicationapis.entities.Post;
import com.shivraj.blog.bloggingapplicationapis.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

}
