package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.MenuDto;

public interface MenuService {
	
	MenuDto createMenu(MenuDto menuDetails, String restaurantName);
	
	MenuDto getMenuById(String menuId);
	
	MenuDto getMenuByRestaurantId(String restaurantId);
	
	boolean deleteMenu(String menuId);

}
