package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;
import com.metehan.app.ws.data.model.entity.MenuEntity;

public interface MenuRepository extends CrudRepository<MenuEntity, Long> {
	
	MenuEntity findByMenuId(String menuId);
	
	MenuEntity findByRestaurantId(Long restaurantId);

}
