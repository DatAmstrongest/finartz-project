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
	
	@NotNull(message="City name of the restaurant cannot be null")
	@Size(min=2, max=300, message="City name of the restaurant must not be less than two characters")
	private String cityName;
	
	@NotNull(message="Province name of the restaurant cannot be null")
	@Size(min=2, max=300, message="Province name of the restaurant must not be less than two characters")
	private String provinceName;
	
	private String restaurantId;

}
