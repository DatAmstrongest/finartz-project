package com.metehan.app.ws.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.FoodRepository;
import com.metehan.app.ws.data.MenuRepository;
import com.metehan.app.ws.data.RestaurantRepository;
import com.metehan.app.ws.data.model.entity.FoodEntity;
import com.metehan.app.ws.data.model.entity.MenuEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;
import com.metehan.app.ws.service.FoodService;
import com.metehan.app.ws.shared.FoodDto;
import com.metehan.app.ws.shared.MenuDto;

@Service
public class FoodServiceImpl implements FoodService {


	
	MenuRepository menuRepository;
	FoodRepository foodRepository;
	RestaurantRepository restaurantRepository;
	@Autowired
	public FoodServiceImpl(MenuRepository menuRepository, FoodRepository foodRepository, RestaurantRepository restaurantRepository)
	{
		this.menuRepository = menuRepository;
		this.foodRepository = foodRepository;
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public FoodDto createFood(FoodDto foodDetails,String restaurantName) {
		foodDetails.setFoodId(UUID.randomUUID().toString());
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		FoodEntity foodEntity = modelMapper.map(foodDetails, FoodEntity.class);
		RestaurantEntity restaurant = restaurantRepository.findByRestaurantName(restaurantName);
		MenuEntity menuEntity = menuRepository.findByRestaurantId(restaurant.getId());
		
		foodEntity.setMenu(menuEntity);;
		foodRepository.save(foodEntity);
		
		
		FoodDto  returnValue = modelMapper.map(foodEntity, FoodDto.class);
		return returnValue;
	}

	
	

}
