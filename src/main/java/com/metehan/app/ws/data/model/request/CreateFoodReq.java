package com.metehan.app.ws.data.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateFoodReq {
	
	@NotNull(message="Food name cannot be null")
	@Size(min=2, message="Food name must not be less than two characters")
	private String foodName;
	
	@Min(0)
	@Max(1500)
	@NotNull(message="Food price cannot be null")
	private int price;
	
	private String foodId;

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
 
	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}
	
	


	
	
	
	

}
