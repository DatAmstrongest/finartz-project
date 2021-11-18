package com.metehan.app.ws.data.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class UpdateFoodReq {
	
	@Size(min=2, message="Food name must not be less than two characters")
	private String foodName;
	
	@Min(0)
	@Max(1500)
	private int foodPrice;
	

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
	
	

 

}
