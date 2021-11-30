package com.metehan.app.ws.shared;

import com.metehan.app.ws.data.model.entity.MenuEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDto {

	private String foodName;

	private int foodPrice;

	private MenuEntity menu;

	private String foodId;

}
