package com.metehan.app.ws.data.model.response;

import java.util.Set;

import com.metehan.app.ws.data.model.entity.FoodEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;

public class CreateMenuRes {
	
    
	private String restaurantId;
	

	private Set<FoodEntity> foods;
	
	private String menuName;

	private String menuId;
	
	

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
