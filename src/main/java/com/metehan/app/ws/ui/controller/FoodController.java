package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateFoodReq;
import com.metehan.app.ws.data.model.request.CreateMenuReq;
import com.metehan.app.ws.data.model.response.CreateFoodRes;
import com.metehan.app.ws.data.model.response.CreateMenuRes;
import com.metehan.app.ws.service.FoodService;
import com.metehan.app.ws.service.MenuService;
import com.metehan.app.ws.shared.FoodDto;
import com.metehan.app.ws.shared.MenuDto;

@SpringBootApplication
@RestController
@RequestMapping(value={"/{restaurantName}/menu/food"})
public class FoodController {
	@Autowired
	FoodService foodService;
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			
			) 
    public ResponseEntity<CreateFoodRes> createMenu(@PathVariable("restaurantName") String restaurantName , @Valid @RequestBody CreateFoodReq foodDetails ) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		FoodDto foodDto = modelMapper.map(foodDetails, FoodDto.class);
		FoodDto createdFood = foodService.createFood(foodDto, restaurantName);
		
		if(createdFood==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
			
		}
		
		CreateFoodRes returnValue = modelMapper.map(createdFood, CreateFoodRes.class);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

}
