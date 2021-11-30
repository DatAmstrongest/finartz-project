package com.metehan.app.ws.data.model.response;

import java.util.Set;

import com.metehan.app.ws.data.model.entity.FoodEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateMenuRes {
	
	private String restaurantId;
	
	private Set<FoodEntity> foods;
	
	private String menuName;

	private String menuId;

}
