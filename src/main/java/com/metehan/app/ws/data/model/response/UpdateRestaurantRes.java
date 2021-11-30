package com.metehan.app.ws.data.model.response;

import com.metehan.app.ws.data.model.entity.RestaurantEntity.State;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRestaurantRes {
	
	private String restaurantName;
	
	private String address;
	
	private State status;
	
}
