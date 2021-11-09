package com.metehan.app.ws.data.model.response;

import com.metehan.app.ws.data.model.entity.RestaurantEntity;
import com.metehan.app.ws.data.model.entity.UserEntity;

public class CreateCommentRes {
	
	private int point;
	 
	private String content;
	
	private String restaurantName;
	
	private String userEmail;

	
	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	private String commentId;

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	
	

}
