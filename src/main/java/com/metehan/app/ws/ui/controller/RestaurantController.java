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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateRestaurantReq;
import com.metehan.app.ws.data.model.request.CreateUserReq;
import com.metehan.app.ws.data.model.request.UpdateRestaurantReq;
import com.metehan.app.ws.data.model.response.CreateRestaurantRes;
import com.metehan.app.ws.data.model.response.CreateUserRes;
import com.metehan.app.ws.data.model.response.UpdateRestaurantRes;
import com.metehan.app.ws.service.RestaurantService;
import com.metehan.app.ws.service.UsersService;
import com.metehan.app.ws.shared.RestaurantDto;
import com.metehan.app.ws.shared.UserDto;

@SpringBootApplication
@RestController
@RequestMapping("/{userId}/restaurant")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	

	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			
			)
	public ResponseEntity<CreateRestaurantRes> createRestaurant(@PathVariable("userId") String userId, @Valid @RequestBody CreateRestaurantReq restaurantDetails ) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		RestaurantDto restaurantDto = modelMapper.map(restaurantDetails, RestaurantDto.class);
		RestaurantDto createdRestaurant = restaurantService.createRestaurant(restaurantDto, userId);
		
		CreateRestaurantRes returnValue = modelMapper.map(createdRestaurant, CreateRestaurantRes.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	
	
	@PutMapping(
			path="/{restaurantName}"		
			)
	public ResponseEntity<UpdateRestaurantRes> updateRestaurant(@PathVariable("userId") String userId, @PathVariable("restaurantName") String restaurantName, @Valid @RequestBody UpdateRestaurantReq restaurantDetails)
	{
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		RestaurantDto restaurantDto = modelMapper.map(restaurantDetails, RestaurantDto.class);
		RestaurantDto createdRestaurant = restaurantService.updateRestaurant(restaurantDto, userId, restaurantName);
		
		UpdateRestaurantRes returnValue = modelMapper.map(createdRestaurant, UpdateRestaurantRes.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}
	
	
	
	@DeleteMapping()
	public String deleteAllRestaurantsofUser(@PathVariable("userId") String userId)
	{
		if(restaurantService.deleteAllRestaurants(userId)) {
			return "All restaurants of  user with id "+userId+" deleted";
		}
		return "Unsuccessful operation";
	}
	
	
	

	@DeleteMapping(
			path="/{restaurantName}"
			)
	public String deleteRestaurant(@PathVariable("userId") String userId, @PathVariable("restaurantName") String restaurantName) {
		
		if(restaurantService.deleteRestaurant(userId, restaurantName)) {
			return "Restaurant with name " + restaurantName+" deleted for user with id "+userId;
		}
		
		return "Unsuccessful operation";
	}

}
