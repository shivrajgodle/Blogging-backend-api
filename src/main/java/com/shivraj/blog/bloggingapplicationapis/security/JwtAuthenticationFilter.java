 package com.shivraj.blog.bloggingapplicationapis.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	//	1) Get token from request
		String requestToken = request.getHeader("Authorization");
		
		//bearer fknksfk22342
		System.out.println(requestToken);
		
		String username = null;
		String token = null;
		
		if(requestToken !=null && requestToken.startsWith("Bearer")) {
			
			 token = requestToken.substring(7);
			 
			 try {
				 username = this.jwtTokenHelper.getUsernameFromToken(token);
			 }
			 catch (IllegalArgumentException e) {
				// TODO: handle exception
				 System.out.println("Unable to get Jwt token");
			}
			catch (ExpiredJwtException e) {
				// TODO: handle exception
				System.out.println("Jwt token Expired");
			}
			 catch (MalformedJwtException e) {
				// TODO: handle exception
				 System.out.println("Invalid jwt ");
			}
			 
		}else
		{
			System.out.println("JWT token does not start with Bearer");
		}
		
		//once we get the token, now validate
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				//sab sahi chal raha hai
				//authentication karna hai
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.println("invalid Jwt token");
			}
		}
		else {
			System.out.println("username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
