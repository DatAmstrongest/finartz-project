package com.metehan.app.ws.shared;

import java.util.Set;



import com.metehan.app.ws.data.model.entity.CommentEntity;
import com.metehan.app.ws.data.model.entity.MenuEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity.State;

public class RestaurantDto {
	
	private String restaurantName;
	
	private String address;
	
	private String restaurantId;

	private State status;
	
	private MenuEntity menu;

    private Set<CommentEntity> comments;

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public State getStatus() {
		return status;
	}

	public void setStatus(State status) {
		this.status = status;
	}

	public MenuEntity getMenu() {
		return menu;
	}

	public void setMenu(MenuEntity menu) {
		this.menu = menu;
	}

	public Set<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(Set<CommentEntity> comments) {
		this.comments = comments;
	}
    
    
    
    

}
