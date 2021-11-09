package com.metehan.app.ws.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.CommentRepository;
import com.metehan.app.ws.data.RestaurantRepository;
import com.metehan.app.ws.data.UsersRepository;
import com.metehan.app.ws.data.model.entity.CommentEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity;
import com.metehan.app.ws.data.model.entity.UserEntity;
import com.metehan.app.ws.data.model.entity.UserEntity.Role;
import com.metehan.app.ws.service.CommentService;
import com.metehan.app.ws.shared.CommentDto;
import com.metehan.app.ws.shared.UserDto;

@Service
public class CommentServiceImpl implements CommentService{
	
	CommentRepository commentRepository;
	UsersRepository usersRepository;
	RestaurantRepository restaurantRepository;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, UsersRepository usersRepository, RestaurantRepository restaurantRepository)
	{
		this.commentRepository = commentRepository;
		this.usersRepository = usersRepository;
		this.restaurantRepository = restaurantRepository;
	}
		

	@Override
	public CommentDto createComment(CommentDto commentDetails, String userId, String restaurantName) {
		UserEntity user = usersRepository.findByUserId(userId);
		RestaurantEntity restaurant = restaurantRepository.findByRestaurantName(restaurantName);
		
		if (user==null) {
			throw new UsernameNotFoundException(userId);
		}
		if(restaurant==null) {
			return null;
		}
		
		commentDetails.setCommentId(UUID.randomUUID().toString());
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		CommentEntity commentEntity = modelMapper.map(commentDetails, CommentEntity.class);
		
		commentEntity.setUser(user);
		commentEntity.setRestaurant(restaurant);
		commentRepository.save(commentEntity);
		
		CommentDto  returnValue = modelMapper.map(commentEntity, CommentDto.class);
		return returnValue;
		
		
		
	}


	@Override
	public boolean deleteComment(String userId, String restaurantName, String commentId) {
		
		UserEntity user = usersRepository.findByUserId(userId);
		
		RestaurantEntity restaurant = restaurantRepository.findByRestaurantName(restaurantName);
		
		CommentEntity [] comments = commentRepository.findByRestaurantId(restaurant.getId());
		
		
		for(int i = 0; i<comments.length; i++) {
			System.out.println(comments[i].getCommentId());
			System.out.println(commentId);
			if(comments[i].getCommentId().equals(commentId)) {
				System.out.println("Merhaba");
				if(comments[i].getUser().getUserId().equals(userId)) {
					commentRepository.delete(comments[i]);
					return true;
					
				}
				
			}
		}
				
		return false;
	}

}
