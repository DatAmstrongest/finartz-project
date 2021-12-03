package com.metehan.app.ws.data.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity()
@Table(name="city")
public class CityEntity implements Serializable {

	private static final long serialVersionUID = 8490171803178636001L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, unique=true)
	private String cityName;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<AddressEntity> addresses;
	
	@Column
	private String cityId;

}
