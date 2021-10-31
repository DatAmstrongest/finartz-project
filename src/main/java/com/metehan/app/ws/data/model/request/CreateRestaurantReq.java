package com.metehan.app.ws.data.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateRestaurantReq {
	
	@NotNull(message="Restaurant name cannot be null")
	@Size(min=2, message="Restaurant name must not be less than two characters")
	private String restaurantName;
	
	
	@NotNull(message="Address cannot be null")
	private String address;
	
	
	private String restaurantId;


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


	public String getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	
	
	

}
