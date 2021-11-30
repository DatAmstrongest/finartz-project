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
@Table(name="foods")
public class FoodEntity implements Serializable {
	
	private static final long serialVersionUID = 5520424100264796508L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String foodName;
	
	@Column(nullable=false, unique=true)
	private int foodPrice;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "menu_id", nullable = false)
	private MenuEntity menu;
	
	@Column(nullable=false, unique=true)
	private String foodId;	

}
