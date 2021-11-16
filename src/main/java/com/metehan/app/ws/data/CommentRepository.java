package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;

import com.metehan.app.ws.data.model.entity.CommentEntity;

public interface CommentRepository extends CrudRepository<CommentEntity, Long>  {
	
	CommentEntity[] findByRestaurantId(Long restaurantId);
	CommentEntity[] findByUserId(Long userId);
	CommentEntity[] findByCommentId(String commentId);

}
