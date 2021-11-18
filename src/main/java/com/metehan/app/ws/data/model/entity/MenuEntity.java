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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<FoodEntity> foods;
	
	@Column(nullable=false, unique=true)
	private String menuId;
	
	@Column(nullable=false)
	private String menuName;
	
	

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

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

	public void setFood(FoodEntity food) {
		this.foods.add(food);
	}
	public void setFoods(Set<FoodEntity> food) {
		this.foods= food;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
	
	

}
