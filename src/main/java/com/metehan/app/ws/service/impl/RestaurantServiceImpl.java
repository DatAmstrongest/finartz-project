package com.metehan.app.ws.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.RestaurantRepository;
import com.metehan.app.ws.data.UsersRepository;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity.State;
import com.metehan.app.ws.data.model.entity.UserEntity;
import com.metehan.app.ws.data.model.entity.UserEntity.Role;
import com.metehan.app.ws.service.RestaurantService;
import com.metehan.app.ws.shared.RestaurantDto;
import com.metehan.app.ws.shared.UserDto;

import io.jsonwebtoken.Claims;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	RestaurantRepository restaurantRepository;
	UsersRepository userRepository;
	
	@Autowired
	public RestaurantServiceImpl(RestaurantRepository restaurantRepository, UsersRepository userRepository)
	{
		this.restaurantRepository = restaurantRepository;
		this.userRepository = userRepository;
	}
	

	@Override
	public RestaurantDto createRestaurant(RestaurantDto restaurantDetails, String userId) {

		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getDetails());
		restaurantDetails.setRestaurantId(UUID.randomUUID().toString());
		restaurantDetails.setStatus(State.WAITING);
		
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		RestaurantEntity restaurantEntity = modelMapper.map(restaurantDetails, RestaurantEntity.class);
		UserEntity user = userRepository.findByUserId(userId);
		
		
		if (user==null) {
			throw new UsernameNotFoundException(userId);
		}
		user.setUserRole(Role.SELLER);
		
		restaurantEntity.setUser(user);
		user.setRestaurants(restaurantEntity);
		 
		restaurantRepository.save(restaurantEntity);
		userRepository.save(user);
		
		
		RestaurantDto  returnValue = modelMapper.map(restaurantEntity, RestaurantDto.class);
		return returnValue;
	}


	@Override
	public boolean deleteRestaurant(String userId, String restaurantName) {
		
		UserEntity user = userRepository.findByUserId(userId);

		RestaurantEntity restaurant = restaurantRepository.findByUserId(user.getId());
		if(restaurant.getRestaurantName().equals(restaurantName)) {
			System.out.println("Merhaba");
			restaurantRepository.delete(restaurant);
			return true;
			
		}
		
		return false;
	}

}
 