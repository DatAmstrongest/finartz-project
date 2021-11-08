package com.metehan.app.ws.data.model.response;

import java.util.Set;

import com.metehan.app.ws.data.model.entity.CommentEntity;
import com.metehan.app.ws.data.model.entity.MenuEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity.State;

public class UpdateRestaurantRes {
	
	private String restaurantName;
	
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
