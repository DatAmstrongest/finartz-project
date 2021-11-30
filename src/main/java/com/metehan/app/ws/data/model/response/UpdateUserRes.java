package com.metehan.app.ws.data.model.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.metehan.app.ws.data.model.entity.AddressEntity;
import com.metehan.app.ws.data.model.entity.UserEntity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRes {
	
	@Size(min=2, message="First name must not be less than two characters")
	private String firstName;

	@Size(min=2, message="Last name must not be less than two characters")
	private String lastName;
	
	@Email
	private String email;
	
	private AddressEntity address;
	
	@Min(0)
	@Max(3)
	private Role userRole;

}
