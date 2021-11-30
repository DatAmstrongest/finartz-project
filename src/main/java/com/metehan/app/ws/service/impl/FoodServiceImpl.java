package com.metehan.app.ws.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.FoodRepository;
import com.metehan.app.ws.data.MenuRepository;
import com.metehan.app.ws.data.RestaurantRepository;
import com.metehan.app.ws.data.model.entity.FoodEntity;
import com.metehan.app.ws.data.model.entity.MenuEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;
import com.metehan.app.ws.service.FoodService;
import com.metehan.app.ws.shared.FoodDto;

@Service
public class FoodServiceImpl implements FoodService {

	private final MenuRepository menuRepository;
	private final FoodRepository foodRepository;
	private final RestaurantRepository restaurantRepository;

	public FoodServiceImpl(MenuRepository menuRepository, FoodRepository foodRepository,
			RestaurantRepository restaurantRepository) {
		this.menuRepository = menuRepository;
		this.foodRepository = foodRepository;
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public FoodDto createFood(FoodDto foodDetails, String restaurantId) {
		foodDetails.setFoodId(UUID.randomUUID().toString());

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		FoodEntity foodEntity = modelMapper.map(foodDetails, FoodEntity.class);
		foodEntity.setFoodPrice(foodDetails.getFoodPrice());

		RestaurantEntity restaurant = restaurantRepository.findByRestaurantId(restaurantId);
		MenuEntity menuEntity = menuRepository.findByRestaurantId(restaurant.getId());

		foodEntity.setMenu(menuEntity);
		;
		foodRepository.save(foodEntity);

		FoodDto returnValue = modelMapper.map(foodEntity, FoodDto.class);
		returnValue.setFoodPrice(foodEntity.getFoodPrice());

		return returnValue;
	}

	@Override
	public boolean deleteFood(String foodId) {

		FoodEntity food = foodRepository.findByFoodId(foodId);

		if (food != null) {

			foodRepository.delete(food);

			return true;
		}

		else {
			return false;

		}
	}

	@Override
	public FoodDto updateFood(FoodDto foodDetails, String foodId) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		FoodEntity foodEntity = foodRepository.findByFoodId(foodId);

		if (foodDetails.getFoodName() != null) {

			foodEntity.setFoodName(foodDetails.getFoodName());

		}
		if (foodDetails.getFoodPrice() != 0) {

			foodEntity.setFoodPrice(foodDetails.getFoodPrice());

		}

		foodRepository.save(foodEntity);

		FoodDto returnValue = modelMapper.map(foodEntity, FoodDto.class);
		returnValue.setFoodPrice(foodEntity.getFoodPrice());
		return returnValue;

	}

}
