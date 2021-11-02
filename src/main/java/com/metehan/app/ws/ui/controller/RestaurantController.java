package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateRestaurantReq;
import com.metehan.app.ws.data.model.request.CreateUserReq;
import com.metehan.app.ws.data.model.response.CreateRestaurantRes;
import com.metehan.app.ws.data.model.response.CreateUserRes;
import com.metehan.app.ws.service.RestaurantService;
import com.metehan.app.ws.service.UsersService;
import com.metehan.app.ws.shared.RestaurantDto;
import com.metehan.app.ws.shared.UserDto;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	

	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			
			)
	public ResponseEntity<CreateRestaurantRes> createRestaurant(@Valid @RequestBody CreateRestaurantReq restaurantDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		RestaurantDto restaurantDto = modelMapper.map(restaurantDetails, RestaurantDto.class);
		
		RestaurantDto createdRestaurant = restaurantService.createRestaurant(restaurantDto);
		
		CreateRestaurantRes returnValue = modelMapper.map(createdRestaurant, CreateRestaurantRes.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@PutMapping
	public String updateRestaurant()
	{
		
		return "update user was called";
		
	}
	
	public String deleteRestaurant() {
		return "delete user was called";
	}

}
