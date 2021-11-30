package com.metehan.app.ws.data.model.request;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserReq {
	
	@NotNull(message="First name cannot be null")
	@Size(min=2, message="First name must not be less than two characters")
	private String firstName;
	
	@NotNull(message="Last name cannot be null")
	@Size(min=2, message="Last name must not be less than two characters")
	private String lastName;
	
	@NotNull(message="Password cannot be null")
	@Size(min=8, max=16, message="Password must be equal or greater than 8 characters and less than 16 characters" )
	private String password;
	
	@NotNull(message="Email cannot be null")
	@Email
	private String email;
	
	@NotNull(message="City name of the user cannot be null")
	@Size(min=2, max=300, message="City name of the restaurant must not be less than two characters")
	private String cityName;
	
	@NotNull(message="Province name of the province cannot be null")
	@Size(min=2, max=300, message="Province name of the restaurant must not be less than two characters")
	private String provinceName;
	
	private String userId;
	
}
