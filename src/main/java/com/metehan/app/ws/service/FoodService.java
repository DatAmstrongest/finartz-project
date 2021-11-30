package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.FoodDto;

public interface FoodService {
	
	FoodDto createFood (FoodDto foodDetails,String restaurantName);
	
	FoodDto updateFood (FoodDto foodDetails, String foodId);
	
	boolean deleteFood(String foodId);

}
