package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateUserReq;
import com.metehan.app.ws.data.model.request.UpdateUserReq;
import com.metehan.app.ws.data.model.request.UserLogin;
import com.metehan.app.ws.data.model.response.CreateUserRes;
import com.metehan.app.ws.data.model.response.UpdateUserRes;
import com.metehan.app.ws.service.AddressService;
import com.metehan.app.ws.service.UsersService;
import com.metehan.app.ws.shared.AddressDto;
import com.metehan.app.ws.shared.UserDto;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UsersService usersService;
	private final AddressService addressService;
	
	public UserController(UsersService usersService, AddressService addressService) {
		
		this.usersService = usersService;
		this.addressService = addressService;
		
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateUserRes[]> getUsers() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto[] users = usersService.getUsers();

		CreateUserRes[] returnValue = modelMapper.map(users, CreateUserRes[].class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

	}

	@GetMapping(path = "/{user-id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateUserRes> getUser(@PathVariable("user-id") String userId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto users = usersService.getUser(userId);

		CreateUserRes returnValue = modelMapper.map(users, CreateUserRes.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateUserRes> createUser(@Valid @RequestBody CreateUserReq userDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		AddressDto createdAddress = addressService.createAddress(userDetails.getAddress());

		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser = usersService.createUser(userDto, createdAddress.getAddressId());
		CreateUserRes returnValue = modelMapper.map(createdUser, CreateUserRes.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public String loginUser(@Valid @RequestBody UserLogin userDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto loggedUser = usersService.login(userDto);
		if (loggedUser == null) {
			return "There is no user with given name";
		}
		return "Login successful";
	}
    
	
	@PutMapping(path = "/{user-id}")
	public ResponseEntity<UpdateUserRes> updateUser(@PathVariable("user-id") String userId,
			@Valid @RequestBody UpdateUserReq userDetails) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto user = usersService.updateUser(userId,userDto);

		if (user != null) {

			UpdateUserRes returnValue = modelMapper.map(user, UpdateUserRes.class);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);

		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

	}

	@DeleteMapping(path = "/{user-id}")
	public String deleteUser(@PathVariable("user-id") String userId) {

		if (usersService.deleteUser(userId)) {
			return "User with id " + userId;
		}

		return "Unsuccessful operation";
	}
}
