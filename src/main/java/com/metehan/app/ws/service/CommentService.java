package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.CommentDto;

public interface CommentService {
	
	CommentDto[] getCommentsOfRestaurant(String restaurantName);
	
	CommentDto[] getCommentsOfUser(String userId);
	
	CommentDto[] getAllComments();
	
	CommentDto[] getComment(String commentId);
	
	CommentDto createComment(CommentDto commentDetails, String userId, String restaurantName);
	
	boolean deleteComment(String userId, String commentId);

}
