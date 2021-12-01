package com.metehan.app.ws.data.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity()
@Table(name="users")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 5992987902956928460L;
	
	public enum Role {
		  USER,
		  ADMIN,
		  SELLER
		  
	}	
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, length=50)
	private String firstName;
	
	@Column(nullable=false, length=50)
	private String lastName;
	
	@Column(nullable=false, length=120, unique=true)
	private String email;
	
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<AddressEntity> addresses;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<CommentEntity> comments;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<RestaurantEntity> restaurants;
	
	@Column(nullable=false, unique=true)
	private String userId;
	
	@Column(nullable=false, unique=true)
	private String encryptedPassword;
	
	@Column(nullable=false)
	public Role userRole;

}
