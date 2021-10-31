package com.metehan.app.ws.data.model.response;

import java.util.Set;

import com.metehan.app.ws.data.model.entity.FoodEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;

public class CreateMenuRes {
	
private RestaurantEntity restaurant;
	

	private Set<FoodEntity> foods;

	private String menuId;

	public RestaurantEntity getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantEntity restaurant) {
		this.restaurant = restaurant;
	}

	public Set<FoodEntity> getFoods() {
		return foods;
	}

	public void setFoods(Set<FoodEntity> foods) {
		this.foods = foods;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
