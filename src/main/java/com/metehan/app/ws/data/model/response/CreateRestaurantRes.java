package com.metehan.app.ws.data.model.response;

import java.util.Set;

import com.metehan.app.ws.data.model.entity.AddressEntity;
import com.metehan.app.ws.data.model.entity.CommentEntity;
import com.metehan.app.ws.data.model.entity.MenuEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity.State;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRestaurantRes {
	
	private String restaurantName;
	
	private String cityName;
	
	private Set<AddressEntity> address;

	private State status;
	
	private MenuEntity menu;

    private Set<CommentEntity> comments;

}
