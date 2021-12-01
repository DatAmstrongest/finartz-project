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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity()
@Table(name="restaurants")
public class RestaurantEntity implements Serializable {

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
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
	
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<AddressEntity> addresses;

}
