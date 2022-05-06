package com.shivraj.blog.bloggingapplicationapis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivraj.blog.bloggingapplicationapis.entities.Category;
import com.shivraj.blog.bloggingapplicationapis.entities.Post;
import com.shivraj.blog.bloggingapplicationapis.entities.User;
import com.shivraj.blog.bloggingapplicationapis.exceptions.ResourceNotFoundException;
import com.shivraj.blog.bloggingapplicationapis.payloads.PostDto;
import com.shivraj.blog.bloggingapplicationapis.repositories.CategoryRepo;
import com.shivraj.blog.bloggingapplicationapis.repositories.PostRepo;
import com.shivraj.blog.bloggingapplicationapis.repositories.UserRepo;
import com.shivraj.blog.bloggingapplicationapis.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer UserId , Integer categoryId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("User", "User id", UserId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageUrl("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
	Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
	List<Post> posts = this.postRepo.findByCategory(category);
	List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> SearchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
