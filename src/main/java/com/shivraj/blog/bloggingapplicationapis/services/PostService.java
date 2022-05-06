package com.shivraj.blog.bloggingapplicationapis.services;

import java.util.List;

import com.shivraj.blog.bloggingapplicationapis.entities.Post;
import com.shivraj.blog.bloggingapplicationapis.payloads.PostDto;

public interface PostService {

	//create
	PostDto createPost(PostDto postDto , Integer UserId , Integer categoryId);
	
	//update
	Post updatePost(PostDto postDto, Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	
	//get All post
	List<Post> getAllPost();
	
	//get post by id
	Post getPostById(Integer postId);
	
	//get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by User
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<Post> SearchPosts(String keyword);
}
