package com.shivraj.blog.bloggingapplicationapis.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shivraj.blog.bloggingapplicationapis.entities.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=4,message="Username must be min 4 charectors !!!")
	private String name;
	
	@Email(message = "Email address is not valid !!!")
	private String email;
	@NotEmpty
	@Size(min=3 , max = 10 , message = "Password must be min of 3 chars and max 10 chars !!!")
	private String password;
	@NotEmpty
	private String about;

	private Set<RoleDto> role = new HashSet<>();

	@JsonIgnore
	public String getPassword(){
		return this.password;
	}


	@JsonProperty
	public void setPassword(String password){
		this.password = password;
	}

}
