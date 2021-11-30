package com.metehan.app.ws.shared;

import java.util.Set;

import com.metehan.app.ws.data.model.entity.CommentEntity;
import com.metehan.app.ws.data.model.entity.MenuEntity;
import com.metehan.app.ws.data.model.entity.RestaurantEntity.State;
import com.metehan.app.ws.data.model.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {

	private String restaurantName;

	private String address;

	private String restaurantId;

	private State status;

	private MenuEntity menu;

	private UserEntity user;

	private Set<CommentEntity> comments;

}
