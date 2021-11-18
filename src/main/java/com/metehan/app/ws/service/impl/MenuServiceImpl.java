package com.metehan.app.ws.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.MenuRepository;
import com.metehan.app.ws.data.RestaurantRepository;
import com.metehan.app.ws.data.UsersRepository;
import com.metehan.app.ws.data.model.entity.MenuEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;
import com.metehan.app.ws.data.model.entity.UserEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity.State;
import com.metehan.app.ws.data.model.entity.UserEntity.Role;
import com.metehan.app.ws.service.MenuService;
import com.metehan.app.ws.shared.MenuDto;
import com.metehan.app.ws.shared.RestaurantDto;

@Service
public class MenuServiceImpl implements MenuService{

	MenuRepository menuRepository;
	RestaurantRepository restaurantRepository;
	
	@Autowired
	public MenuServiceImpl(MenuRepository menuRepository, RestaurantRepository restaurantRepository)
	{
		this.menuRepository = menuRepository;
		this.restaurantRepository = restaurantRepository;
	}
	
	@Override
	public MenuDto createMenu(MenuDto menuDetails, String restaurantId) {

		menuDetails.setMenuId(UUID.randomUUID().toString());
		
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		MenuEntity menuEntity = modelMapper.map(menuDetails, MenuEntity.class);
		RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(restaurantId);
		
		if(restaurantEntity==null) {
			return null;
		}
		if (restaurantEntity.getStatus()!= State.APPROVED) {
			return null;
		}
		menuEntity.setRestaurant(restaurantEntity);
		restaurantEntity.setMenu(menuEntity);
		
		menuRepository.save(menuEntity);
		restaurantRepository.save(restaurantEntity);
	
		
		
		MenuDto  returnValue = modelMapper.map(menuEntity, MenuDto.class);
		return returnValue;
		
	}

	@Override
	public MenuDto getMenuById(String menuId) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		MenuEntity menu = menuRepository.findByMenuId(menuId);
		
		MenuDto  returnValue = modelMapper.map(menu, MenuDto.class);
		
		return returnValue;
		
		
	}

	@Override
	public MenuDto getMenuByRestaurantId(String restaurantId) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		RestaurantEntity restaurant = restaurantRepository.findByRestaurantId(restaurantId);
		MenuEntity menu = menuRepository.findByRestaurantId(restaurant.getId());
		
		MenuDto  returnValue = modelMapper.map(menu, MenuDto.class);
		
		return returnValue;
	}

	@Override
	public boolean deleteMenu(String menuId) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		MenuEntity menu = menuRepository.findByMenuId(menuId);
		
		if(menu==null) {
			return false;
		}
		else {
			menuRepository.delete(menu);
			return true;
		}
		
		
	}

}
