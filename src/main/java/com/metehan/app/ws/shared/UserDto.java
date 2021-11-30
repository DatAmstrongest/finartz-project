package com.metehan.app.ws.shared;

import java.io.Serializable;

import com.metehan.app.ws.data.model.entity.AddressEntity;
import com.metehan.app.ws.data.model.entity.UserEntity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Serializable {

	private static final long serialVersionUID = -5619898394497158418L;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private AddressEntity address;
	
	private String password;
	
	private String userId;
	
	private String encryptedPassword;
	
	private Role userRole;

}
