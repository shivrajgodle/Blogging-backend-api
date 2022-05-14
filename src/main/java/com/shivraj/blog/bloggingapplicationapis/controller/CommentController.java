package com.shivraj.blog.bloggingapplicationapis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivraj.blog.bloggingapplicationapis.payloads.ApiResponce;
import com.shivraj.blog.bloggingapplicationapis.payloads.CommentDto;
import com.shivraj.blog.bloggingapplicationapis.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto , @PathVariable Integer postId){
		
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentID}")
	public ResponseEntity<ApiResponce> deleteComment(@PathVariable Integer commentID){
		
		this.commentService.DeleteComment(commentID);
		
		return new ResponseEntity<ApiResponce>(new ApiResponce("Comment deleted successfully !!!",true),HttpStatus.OK);
	}
}
