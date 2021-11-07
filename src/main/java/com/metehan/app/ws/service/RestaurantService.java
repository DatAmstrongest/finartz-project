package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.RestaurantDto;

public interface RestaurantService {
	
	RestaurantDto createRestaurant(RestaurantDto restaurantDetails, String userId);
	boolean deleteRestaurant(String userId, String restaurantName);

}
