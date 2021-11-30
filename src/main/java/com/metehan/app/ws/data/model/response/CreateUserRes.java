package com.metehan.app.ws.data.model.response;

import com.metehan.app.ws.data.model.entity.AddressEntity;
import com.metehan.app.ws.data.model.entity.UserEntity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRes {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private AddressEntity address;
	
	private String userId;
	
	private Role userRole;

}
