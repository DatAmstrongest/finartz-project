package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;

import com.metehan.app.ws.data.model.entity.CommentEntity;
import com.metehan.app.ws.data.model.entity.MenuEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;

public interface MenuRepository extends CrudRepository<MenuEntity, Long> {
	
	MenuEntity findByMenuId(String menuId);
	MenuEntity findByRestaurantId(Long restaurantId);

}
