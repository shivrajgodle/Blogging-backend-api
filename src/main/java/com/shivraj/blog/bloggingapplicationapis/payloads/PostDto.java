package com.shivraj.blog.bloggingapplicationapis.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.shivraj.blog.bloggingapplicationapis.entities.Category;
import com.shivraj.blog.bloggingapplicationapis.entities.Comment;
import com.shivraj.blog.bloggingapplicationapis.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
}
