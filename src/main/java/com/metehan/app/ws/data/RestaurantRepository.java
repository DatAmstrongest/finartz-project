package com.metehan.app.ws.data;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;

public interface RestaurantRepository extends CrudRepository<RestaurantEntity, Long> {
	
	Set<RestaurantEntity> findAll();
	
	RestaurantEntity[] findByUserId(Long userId);
	
	RestaurantEntity findByRestaurantId(String restaurantId);
	
	RestaurantEntity findByRestaurantName(String restaurantName);
}
