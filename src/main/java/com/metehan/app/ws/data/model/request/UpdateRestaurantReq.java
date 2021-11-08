package com.metehan.app.ws.data.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.metehan.app.ws.data.model.entity.MenuEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity.State;

public class UpdateRestaurantReq {
	
	@Size(min=2, message="Restaurant name must not be less than two characters")
	private String restaurantName;
	
	@Size(min=2, max=300, message="Restaurant address must not be less than two characters")
	private String address;
	
	private State status;
	
	


	public String getRestaurantName() {
		return restaurantName;
	}


	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public State getStatus() {
		return status;
	}


	public void setStatus(State status) {
		this.status = status;
	}
	
	
	


}
