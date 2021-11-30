package com.metehan.app.ws.data.model.request;


import javax.validation.constraints.Size;
import com.metehan.app.ws.data.model.entity.RestaurantEntity.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRestaurantReq {
	
	@Size(min=2, message="Restaurant name must not be less than two characters")
	private String restaurantName;
	
	@Size(min=2, max=300, message="Restaurant address must not be less than two characters")
	private String address;
	
	private State status;

}
