package com.metehan.app.ws.shared;

import com.metehan.app.ws.data.model.entity.RestaurantEntity;
import com.metehan.app.ws.data.model.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

	private int point;

	private String content;

	private UserEntity user;

	private RestaurantEntity restaurant;

	private String commentId;

}
