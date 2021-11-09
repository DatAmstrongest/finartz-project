package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDetails, String userId, String restaurantName);
	boolean deleteComment(String userId, String restaurantName, String commentId);

}
