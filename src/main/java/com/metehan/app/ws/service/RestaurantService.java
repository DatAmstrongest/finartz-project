package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.RestaurantDto;

public interface RestaurantService {
	
	RestaurantDto createRestaurant(RestaurantDto restaurantDetails, String userId, String addressId);
	
	RestaurantDto updateRestaurant(RestaurantDto restaurantDetails, String userId, String restaurantName);
	
	RestaurantDto [] getRestaurantsOfUser(String userId);
	
	RestaurantDto[] getCloseCityRestaurants(String userId);
	
	RestaurantDto[] getCloseProvinceRestaurants(String userId);
	
	boolean deleteRestaurant(String userId, String restaurantName);
	
	boolean deleteAllRestaurantsOfUser(String userId);

}
