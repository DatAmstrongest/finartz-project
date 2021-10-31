package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateUserReq;
import com.metehan.app.ws.data.model.response.CreateUserRes;
import com.metehan.app.ws.service.UsersService;
import com.metehan.app.ws.shared.UserDto;



@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UsersService usersService;
	

	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			
			)
	public ResponseEntity<CreateUserRes> createUser(@Valid @RequestBody CreateUserReq userDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto createdUser = usersService.createUser(userDto);
		
		CreateUserRes returnValue = modelMapper.map(createdUser, CreateUserRes.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@PutMapping
	public String updateUser()
	{
		return "update user was called";
		
	}
	
	public String deleteUser() {
		return "delete user was called";
	}
}
