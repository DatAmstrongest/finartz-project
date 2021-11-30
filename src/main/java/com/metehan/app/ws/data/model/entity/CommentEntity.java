package com.metehan.app.ws.data.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity()
@Table(name="comments")
public class CommentEntity implements Serializable {

	private static final long serialVersionUID = -3364401869054326603L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private int point;
	 
	@Column(nullable=false, length=300)
	private String content;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "restaurant_id", nullable = false)
	private RestaurantEntity restaurant;
	
	@Column(nullable=false, unique=true)
	private  String commentId;
	
}
