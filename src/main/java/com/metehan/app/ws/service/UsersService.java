package com.metehan.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.metehan.app.ws.shared.UserDto;

public interface UsersService extends UserDetailsService {
	UserDto createUser(UserDto userDetails);
	
	UserDto getUser(String userId);
	UserDto[] getUsers();
	UserDto getUserDetailsByEmail(String email);
	
	boolean deleteUser(String userId);
	
	UserDto updateUser(UserDto userDetails, String userId);
	
	UserDto login(UserDto userDetails);

}
   