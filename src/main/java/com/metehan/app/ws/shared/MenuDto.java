package com.metehan.app.ws.shared;

import java.util.Set;

import com.metehan.app.ws.data.model.entity.FoodEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {

	private RestaurantEntity restaurant;

	private String menuName;

	private Set<FoodEntity> foods;

	private String menuId;

}
