package com.metehan.app.ws.data.model.response;

public class UpdateFoodRes {
	
	private int foodPrice;
	 
	private String foodName;
	
	
	private String foodId;


	public int getFoodPrice() {
		return foodPrice;
	}


	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}


	public String getFoodName() {
		return foodName;
	}


	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}


	public String getFoodId() {
		return foodId;
	}


	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

}
