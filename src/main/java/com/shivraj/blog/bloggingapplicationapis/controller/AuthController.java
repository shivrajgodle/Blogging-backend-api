package com.shivraj.blog.bloggingapplicationapis.controller;

import com.shivraj.blog.bloggingapplicationapis.entities.User;
import com.shivraj.blog.bloggingapplicationapis.exceptions.ApiException;
import com.shivraj.blog.bloggingapplicationapis.payloads.UserDto;
import com.shivraj.blog.bloggingapplicationapis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.shivraj.blog.bloggingapplicationapis.payloads.JwtAuthRequest;
import com.shivraj.blog.bloggingapplicationapis.payloads.JwtAuthResponce;
import com.shivraj.blog.bloggingapplicationapis.security.JwtTokenHelper;



@RestController
@RequestMapping("/api/v1/auth/")

public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponce> createToken(@RequestBody JwtAuthRequest request) throws Exception{
	
		this.authenticate(request.getUsername(),request.getPassword());
		 UserDetails userDetails  = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponce responce = new JwtAuthResponce();
		responce.setToken(token);
		responce.setUser(this.mapper.map((User) userDetails , UserDto.class));
		return new ResponseEntity<JwtAuthResponce>(responce,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new ApiException("invalid username or password !!!");
		}
	}


	//Register New User api

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){

		UserDto registeredUser =this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}



}
