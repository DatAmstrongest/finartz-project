package com.metehan.app.ws.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final UsersRepository usersRepository;
	private final RestaurantRepository restaurantRepository;

	public CommentServiceImpl(CommentRepository commentRepository, UsersRepository usersRepository, RestaurantRepository restaurantRepository) {
		this.commentRepository = commentRepository;
		this.usersRepository = usersRepository;
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public CommentDto createComment(CommentDto commentDetails, String userId, String restaurantId) {
		UserEntity user = usersRepository.findByUserId(userId);
		RestaurantEntity restaurant = restaurantRepository.findByRestaurantId(restaurantId);

		if (user == null) {
			throw new UsernameNotFoundException(userId);
		}
		if (restaurant == null) {
			return null;
		}

		commentDetails.setCommentId(UUID.randomUUID().toString());

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		CommentEntity commentEntity = modelMapper.map(commentDetails, CommentEntity.class);
		commentEntity.setPoint(commentDetails.getPoint());

		commentEntity.setUser(user);
		commentEntity.setRestaurant(restaurant);
		commentRepository.save(commentEntity);

		CommentDto returnValue = modelMapper.map(commentEntity, CommentDto.class);
		returnValue.setPoint(commentEntity.getPoint());
		return returnValue;

	}

	@Override
	public boolean deleteComment(String userId, String commentId) {
		
		CommentEntity [] comment = commentRepository.findByCommentId(commentId);
		UserEntity user = usersRepository.findByUserId(userId);
		
		if(user.getUserRole() == Role.ADMIN) {
			if(comment!=null) {
				  commentRepository.delete(comment[0]);
				  return true;
			}	
		}
		else {
			if(comment!=null) {
				if(user.getId() == comment[0].getId()) {
					commentRepository.delete(comment[0]);
					return true;
				}
				
			}
		}
		
		return false;
	}

	@Override
	public CommentDto[] getCommentsOfRestaurant(String restaurantId) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		RestaurantEntity restaurant = restaurantRepository.findByRestaurantId(restaurantId);
		CommentEntity[] comments = commentRepository.findByRestaurantId(restaurant.getId());

		CommentDto[] returnValue = modelMapper.map(comments, CommentDto[].class);
		return returnValue;

	}

	@Override
	public CommentDto[] getCommentsOfUser(String userId) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity user = usersRepository.findByUserId(userId);
		CommentEntity[] comments = commentRepository.findByUserId(user.getId());

		CommentDto[] returnValue = modelMapper.map(comments, CommentDto[].class);
		return returnValue;
	}

	@Override
	public CommentDto[] getComment(String commentId) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		CommentEntity [] comment = commentRepository.findByCommentId(commentId);

		CommentDto[] returnValue = modelMapper.map(comment, CommentDto[].class);
		return returnValue;
	}

	@Override
	public CommentDto[] getAllComments() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Iterable<CommentEntity> comments = commentRepository.findAll();
		CommentDto[] returnValue = modelMapper.map(comments, CommentDto[].class);

		return returnValue;
	}

}
