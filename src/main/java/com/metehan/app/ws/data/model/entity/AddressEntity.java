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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity()
@Table(name="address")
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = -2054998064859959781L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "city_id", nullable = false)
	private CityEntity city;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "province_id", nullable = false)
	private ProvinceEntity province;
	
	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable (name = "restaurant_addresses", joinColumns = @JoinColumn(name = "address_id"), inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
	private Set<RestaurantEntity> restaurants;

	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable (name = "user_addresses", joinColumns = @JoinColumn(name = "address_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<UserEntity> users;
	
	@Column
	private String addressId;

}
