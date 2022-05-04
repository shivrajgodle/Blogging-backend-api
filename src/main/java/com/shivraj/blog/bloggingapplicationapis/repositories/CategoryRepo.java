package com.shivraj.blog.bloggingapplicationapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivraj.blog.bloggingapplicationapis.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
