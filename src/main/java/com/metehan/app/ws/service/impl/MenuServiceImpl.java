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
	RestaurantRepository restaurantRespository;
	
	@Autowired
	public MenuServiceImpl(MenuRepository menuRepository, RestaurantRepository restaurantRepository)
	{
		this.menuRepository = menuRepository;
		this.restaurantRespository = restaurantRepository;
	}
	
	@Override
	public MenuDto createMenu(MenuDto menuDetails, String restaurantName) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		menuDetails.setMenuId(UUID.randomUUID().toString());
		
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		MenuEntity menuEntity = modelMapper.map(menuDetails, MenuEntity.class);
		RestaurantEntity restaurantEntity = restaurantRespository.findByRestaurantName(restaurantName);
		menuEntity.setRestaurant(restaurantEntity);
		restaurantEntity.setMenu(menuEntity);
		
		menuRepository.save(menuEntity);
		restaurantRespository.save(restaurantEntity);
	
		
		
		MenuDto  returnValue = modelMapper.map(menuEntity, MenuDto.class);
		return returnValue;
		
	}

}
