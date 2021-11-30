package com.metehan.app.ws.service.impl;

import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private final RestaurantRepository restaurantRepository;
	private final UsersRepository userRepository;

	public RestaurantServiceImpl(RestaurantRepository restaurantRepository, UsersRepository userRepository) {
		this.restaurantRepository = restaurantRepository;
		this.userRepository = userRepository;
	}

	@Override
	public RestaurantDto createRestaurant(RestaurantDto restaurantDetails, String userId) {

		restaurantDetails.setRestaurantId(UUID.randomUUID().toString());
		restaurantDetails.setStatus(State.WAITING);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		RestaurantEntity restaurantEntity = modelMapper.map(restaurantDetails, RestaurantEntity.class);
		UserEntity user = userRepository.findByUserId(userId);

		RestaurantDto returnValue;

		if (user == null) {
			throw new UsernameNotFoundException(userId);
		} else if (user.getUserRole() == Role.SELLER) {

			restaurantEntity.setUser(user);
			restaurantRepository.save(restaurantEntity);
			returnValue = modelMapper.map(restaurantEntity, RestaurantDto.class);

		} else {

			returnValue = null;

		}

		return returnValue;
	}

	@Override
	public boolean deleteAllRestaurantsOfUser(String userId) {

		UserEntity user = userRepository.findByUserId(userId);
		RestaurantEntity[] restaurants = restaurantRepository.findByUserId(user.getId());

		if (restaurants == null) {
			return false;
		} else {
			for (int i = 0; i < restaurants.length; i++) {

				restaurantRepository.delete(restaurants[i]);

			}
			return true;

		}

	}

	@Override
	public boolean deleteRestaurant(String userId, String restaurantId) {

		UserEntity user = userRepository.findByUserId(userId);
		RestaurantEntity restaurant1 = restaurantRepository.findByRestaurantId(restaurantId);
		if (user.getUserRole() == Role.ADMIN) {
			restaurantRepository.delete(restaurant1);
			return true;	
		}
		RestaurantEntity[] restaurant = restaurantRepository.findByUserId(user.getId());
		
		for (int i = 0; i < restaurant.length; i++) {
			if (restaurant[i].getRestaurantName().equals(restaurant1.getRestaurantName())) {
				restaurantRepository.delete(restaurant1);
				return true;
			}
		}

		return false;
	}

	@Override
	public RestaurantDto updateRestaurant(RestaurantDto restaurantDetails, String userId, String restaurantId) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity user = userRepository.findByUserId(userId);
		if (user == null) {
			throw new UsernameNotFoundException(userId);
		}

		RestaurantEntity[] restaurant = restaurantRepository.findByUserId(user.getId());
		RestaurantEntity restaurant1 = restaurantRepository.findByRestaurantId(restaurantId);
		for (int i = 0; i < restaurant.length; i++) {
			if (restaurant[i].getRestaurantName().equals(restaurant1.getRestaurantName())) {

				if (restaurantDetails.getRestaurantName() != null) {
					restaurant[i].setRestaurantName(restaurantDetails.getRestaurantName());
				}

				if (restaurantDetails.getStatus() != null) {
					restaurant[i].setStatus(restaurantDetails.getStatus());
				}
				restaurantRepository.save(restaurant[i]);

				RestaurantDto returnValue = modelMapper.map(restaurant[i], RestaurantDto.class);
				return returnValue;

			}
		}
		return null;
	}

	@Override
	public RestaurantDto[] getRestaurantsOfUser(String userId) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity user = userRepository.findByUserId(userId);
		RestaurantEntity[] restaurants = restaurantRepository.findByUserId(user.getId());

		RestaurantDto[] returnValue = modelMapper.map(restaurants, RestaurantDto[].class);

		return returnValue;
	}

}
