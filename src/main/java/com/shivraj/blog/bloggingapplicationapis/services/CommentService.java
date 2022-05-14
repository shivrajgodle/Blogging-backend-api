package com.shivraj.blog.bloggingapplicationapis.services;

import com.shivraj.blog.bloggingapplicationapis.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto , Integer postId);
	void DeleteComment(Integer commentID);
}
