package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateUserReq;
import com.metehan.app.ws.data.model.request.UpdateRestaurantReq;
import com.metehan.app.ws.data.model.request.UpdateUserReq;
import com.metehan.app.ws.data.model.request.UserLogin;
import com.metehan.app.ws.data.model.response.CreateRestaurantRes;
import com.metehan.app.ws.data.model.response.CreateUserRes;
import com.metehan.app.ws.data.model.response.UpdateRestaurantRes;
import com.metehan.app.ws.data.model.response.UpdateUserRes;
import com.metehan.app.ws.service.UsersService;
import com.metehan.app.ws.shared.RestaurantDto;
import com.metehan.app.ws.shared.UserDto;


@SpringBootApplication
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UsersService usersService;
	
	
	
	@GetMapping(
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<CreateUserRes[]> getUsers()
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto [] users = usersService.getUsers();
		
		CreateUserRes [] returnValue = modelMapper.map(users, CreateUserRes[].class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}
	
	@GetMapping(
			path="{userId}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<CreateUserRes> getUser(@PathVariable("userId") String userId)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto users = usersService.getUser(userId);
		
		CreateUserRes returnValue = modelMapper.map(users, CreateUserRes.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}
	
	
	
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
	
	@PostMapping(
			path="login",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public String loginUser(@Valid @RequestBody UserLogin userDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto loggedUser = usersService.login(userDto);
		if(loggedUser==null) {
			return "There is no user with given name";
		}
		return "Login successful";
	}
	
	@PutMapping(path="/{userId}")
	public ResponseEntity<UpdateUserRes> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody UpdateUserReq userDetails)
	{
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto user = usersService.updateUser(userDto, userId);
		
		UpdateUserRes returnValue = modelMapper.map(user, UpdateUserRes.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}	
	
	@DeleteMapping(
			path="/{userId}"
	)
	public String deleteUser(@PathVariable("userId") String userId) {
		
		if(usersService.deleteUser(userId)) {
			return "User with id "+userId;
		}
		
		return "Unsuccessful operation";
				
		
		
				
	}
}
