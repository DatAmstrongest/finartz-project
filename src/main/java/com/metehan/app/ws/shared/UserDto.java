package com.metehan.app.ws.shared;

import java.io.Serializable;
import java.util.Set;

import com.metehan.app.ws.data.model.entity.AddressEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;
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
	
	private Set<AddressEntity> addresses;
	
	private Set<RestaurantEntity> restaurants;
	
	private String password;
	
	private String userId;
	
	private String encryptedPassword;
	
	private Role userRole;

}
