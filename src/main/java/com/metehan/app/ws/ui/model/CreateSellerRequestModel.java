package com.metehan.app.ws.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateSellerRequestModel {
	

	@NotNull(message="Restaurant name cannot be null")
	@Size(min=2, message="Restaurant name must not be less than two characters")
	private String restaurantName;
	
	@NotNull(message="Password cannot be null")
	@Size(min=8, max=16, message="Password must be equal or greater than 8 characters and less than 16 characters" )
	private String password;
	

	@NotNull(message="Email cannot be null")
	@Email
	private String email;
	
	@NotNull(message="Email cannot be null")
	private String address;
	
	private String restaurantId;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	


}
