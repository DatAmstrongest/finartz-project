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
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateFoodReq;
import com.metehan.app.ws.data.model.request.CreateMenuReq;
import com.metehan.app.ws.data.model.request.UpdateFoodReq;
import com.metehan.app.ws.data.model.request.UpdateRestaurantReq;
import com.metehan.app.ws.data.model.response.CreateFoodRes;
import com.metehan.app.ws.data.model.response.CreateMenuRes;
import com.metehan.app.ws.data.model.response.UpdateFoodRes;
import com.metehan.app.ws.data.model.response.UpdateRestaurantRes;
import com.metehan.app.ws.service.FoodService;
import com.metehan.app.ws.service.MenuService;
import com.metehan.app.ws.shared.FoodDto;
import com.metehan.app.ws.shared.MenuDto;
import com.metehan.app.ws.shared.RestaurantDto;

@SpringBootApplication
@RestController
@RequestMapping(value={"restaurant/{restaurantId}/menu/food"})
public class FoodController {
	@Autowired
	FoodService foodService;
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			
			) 
    public ResponseEntity<CreateFoodRes> createFood(@PathVariable("restaurantId") String restaurantId , @Valid @RequestBody CreateFoodReq foodDetails ) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		FoodDto foodDto = modelMapper.map(foodDetails, FoodDto.class);
		foodDto.setFoodPrice(foodDetails.getPrice());
		FoodDto createdFood = foodService.createFood(foodDto, restaurantId);
		
		if(createdFood==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
			
		}
		
		CreateFoodRes returnValue = modelMapper.map(createdFood, CreateFoodRes.class);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@PutMapping(
			path="/{foodId}"		
			)
	public ResponseEntity<UpdateFoodRes> updateFood(@PathVariable("foodId") String foodId, @Valid @RequestBody UpdateFoodReq foodDetails)
	{
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		FoodDto foodDto = modelMapper.map(foodDetails, FoodDto.class);
		foodDto.setFoodPrice(foodDetails.getFoodPrice());
		FoodDto food = foodService.updateFood(foodDto, foodId);
		
		UpdateFoodRes returnValue = modelMapper.map(food, UpdateFoodRes.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}
	
	@DeleteMapping(
			path="/{foodId}"
			)
	public String deleteFood(@PathVariable("foodId") String foodId) {
		
		if(foodService.deleteFood(foodId)) {
			return "Food is delete with id "+foodId;
		}
		
		return "Unsuccessful operation";
	}

}
