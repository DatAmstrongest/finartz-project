package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.RestaurantDto;

public interface RestaurantService {
	
	RestaurantDto createRestaurant(RestaurantDto restaurantDetails, String userId);
	RestaurantDto updateRestaurant(RestaurantDto restaurantDetails, String userId, String restaurantName);
	RestaurantDto getRestaurantOfUser(String restaurantName, String userId);
	RestaurantDto [] getRestaurantsOfUser(String userId);
	boolean deleteRestaurant(String userId, String restaurantName);
	boolean deleteAllRestaurantsOfUser(String userId);

}
