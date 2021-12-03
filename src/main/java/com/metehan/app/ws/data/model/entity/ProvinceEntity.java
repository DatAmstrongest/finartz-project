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
@Table(name="province")
public class ProvinceEntity implements Serializable {
	
	private static final long serialVersionUID = 4084992210033810553L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, unique = true)
	private String provinceName;
	
	@JsonBackReference
	@OneToMany(mappedBy = "province", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<AddressEntity> addresses;
	
	@Column
	private String provinceId;

}
