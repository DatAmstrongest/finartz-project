package com.metehan.app.ws.data.model.response;

import com.metehan.app.ws.data.model.entity.AddressEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity.State;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRestaurantRes {
	
	private String restaurantName;
	
	private AddressEntity address;
	
	private State status;
	
}
