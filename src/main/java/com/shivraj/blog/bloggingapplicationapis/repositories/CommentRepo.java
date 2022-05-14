package com.shivraj.blog.bloggingapplicationapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivraj.blog.bloggingapplicationapis.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
