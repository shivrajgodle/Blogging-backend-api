package com.shivraj.blog.bloggingapplicationapis.payloads;

import lombok.Data;

@Data
public class JwtAuthResponce {

	private String token;

	private UserDto user;
}
