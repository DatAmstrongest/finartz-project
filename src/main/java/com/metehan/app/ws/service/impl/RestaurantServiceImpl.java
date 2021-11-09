package com.metehan.app.ws.service.impl;

import java.util.List;
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
	public boolean deleteAllRestaurantsOfUser(String userId) {
		
		UserEntity user = userRepository.findByUserId(userId);
		RestaurantEntity[] restaurants= restaurantRepository.findByUserId(user.getId());
		
		if(restaurants == null) {
			return false;
		}
		else {
			for(int i=0; i<restaurants.length; i++) {
				
				restaurantRepository.delete(restaurants[i]);
			
			}
			return true;
			
		}
		
		
		    
		    
		
	}
	
	@Override
	public boolean deleteRestaurant(String userId, String restaurantName) {
		
		UserEntity user = userRepository.findByUserId(userId);

		RestaurantEntity[] restaurant = restaurantRepository.findByUserId(user.getId());
		for(int i=0; i<restaurant.length; i++) {
			if(restaurant[i].getRestaurantName().equals(restaurantName)) {
				return true;
			}
		}
		
		return false;
	}


	@Override
	public RestaurantDto updateRestaurant(RestaurantDto restaurantDetails, String userId, String restaurantName) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		UserEntity user = userRepository.findByUserId(userId);
		if(user==null) {
			throw new UsernameNotFoundException(userId);
		}
		
		RestaurantEntity[] restaurant = restaurantRepository.findByUserId(user.getId());
		for(int i=0; i<restaurant.length; i++) {
			if(restaurant[i].getRestaurantName().equals(restaurantName)) {
				if(restaurantDetails.getAddress()!=null) {
					restaurant[i].setAddress(restaurantDetails.getAddress());
				}
				
				if(restaurantDetails.getRestaurantName()!=null) {
					restaurant[i].setRestaurantName(restaurantDetails.getRestaurantName());
				}
				
				if(restaurantDetails.getStatus()!=null) {
					restaurant[i].setStatus(restaurantDetails.getStatus());
				}
				restaurantRepository.save(restaurant[i]);
				
				RestaurantDto  returnValue = modelMapper.map(restaurant[i], RestaurantDto.class);
				return returnValue;
				
				
			}
		}
		return null;
	}



}
 