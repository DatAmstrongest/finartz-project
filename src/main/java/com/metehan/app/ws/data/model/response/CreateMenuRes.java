package com.metehan.app.ws.data.model.response;

import java.util.Set;

import com.metehan.app.ws.data.model.entity.FoodEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;

public class CreateMenuRes {
	
    
	private String restaurantName;
	

	private Set<FoodEntity> foods;
	
	private String menuName;

	private String menuId;
	
	

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}



	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
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
