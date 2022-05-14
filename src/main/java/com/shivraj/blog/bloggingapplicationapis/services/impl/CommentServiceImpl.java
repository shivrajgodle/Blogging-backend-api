package com.shivraj.blog.bloggingapplicationapis.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivraj.blog.bloggingapplicationapis.entities.Comment;
import com.shivraj.blog.bloggingapplicationapis.entities.Post;
import com.shivraj.blog.bloggingapplicationapis.exceptions.ResourceNotFoundException;
import com.shivraj.blog.bloggingapplicationapis.payloads.CommentDto;
import com.shivraj.blog.bloggingapplicationapis.payloads.PostDto;
import com.shivraj.blog.bloggingapplicationapis.repositories.CommentRepo;
import com.shivraj.blog.bloggingapplicationapis.repositories.PostRepo;
import com.shivraj.blog.bloggingapplicationapis.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void DeleteComment(Integer commentID) {
		// TODO Auto-generated method stub

		Comment comment = this.commentRepo.findById(commentID).orElseThrow(()->new ResourceNotFoundException("Comment", "commentID", commentID));
		this.commentRepo.delete(comment); 
	}

}
