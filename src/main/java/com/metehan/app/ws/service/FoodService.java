package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.FoodDto;

public interface FoodService {
	
	FoodDto createFood(FoodDto foodDetails, String restaurantName);

}
