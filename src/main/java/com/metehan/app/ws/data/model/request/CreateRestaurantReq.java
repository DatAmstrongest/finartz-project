package com.metehan.app.ws.data.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRestaurantReq {
	
	@NotNull(message="Restaurant name cannot be null")
	@Size(min=2, max=100, message="Restaurant name must not be less than two characters")
	private String restaurantName;
	
	@NotNull(message="Address cannot be null")
	private CreateAddressReq address;
	
	private String restaurantId;

}
