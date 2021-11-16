package com.metehan.app.ws.data.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity()
@Table(name="restaurants")
public class RestaurantEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4959683704024060046L;
	
	public enum State{
		WAITING,
		APPROVED,
		REJECTED
	}
	
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, length=50)
	private String restaurantName;
	
	
	@Column(nullable=false, length=120, unique=true)
	private String address;
	
	@Column(nullable=false, unique=true)
	private String restaurantId;
	
	@Column(nullable=false)
	private State status;
	
	@JsonManagedReference
	@OneToOne(mappedBy = "restaurant", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private MenuEntity menu;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<CommentEntity> comments;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
	

	
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Set<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(Set<CommentEntity> comments) {
		this.comments = comments;
	}

	public MenuEntity getMenu() {
		return menu;
	}

	public void setMenu(MenuEntity menu) {
		this.menu = menu;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
	
	
	
	





}
