package com.metehan.app.ws.shared;



import com.metehan.app.ws.data.model.entity.MenuEntity;

public class FoodDto {
	

	
	private String foodName;
	

	private int foodPrice;
	
	private MenuEntity menu;

	private String foodId;

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}

	public MenuEntity getMenu() {
		return menu;
	}

	public void setMenu(MenuEntity menu) {
		this.menu = menu;
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}
	
	

}
