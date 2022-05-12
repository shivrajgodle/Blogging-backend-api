package com.shivraj.blog.bloggingapplicationapis.services;

import java.util.List;

import com.shivraj.blog.bloggingapplicationapis.entities.Post;
import com.shivraj.blog.bloggingapplicationapis.payloads.PostDto;
import com.shivraj.blog.bloggingapplicationapis.payloads.PostResponce;

public interface PostService {

	//create
	PostDto createPost(PostDto postDto , Integer UserId , Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	
	//get All post
	PostResponce getAllPost(Integer pageNumber , Integer pageSize , String sortBy , String sortDir);
	
	//get post by id
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by User
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<PostDto> SearchPosts(String keyword);
}
