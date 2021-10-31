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

@Entity()
@Table(name="menus")
public class MenuEntity implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1600282797998107320L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;
	
	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<FoodEntity> foods;
	
	@Column(nullable=false, unique=true)
	private String menuId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RestaurantEntity getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantEntity restaurant) {
		this.restaurant = restaurant;
	}

	public Set<FoodEntity> getFoods() {
		return foods;
	}

	public void setFoods(Set<FoodEntity> foods) {
		this.foods = foods;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
	
	

}
