package com.shivraj.blog.bloggingapplicationapis.repositories;

import com.shivraj.blog.bloggingapplicationapis.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Integer> {


}
