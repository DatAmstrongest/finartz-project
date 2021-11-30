package com.metehan.app.ws.data.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRes {
	
	private int point;
	 
	private String content;
	
	private String restaurantName;
	
	private String userEmail;
	
	private String commentId;

}
