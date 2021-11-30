package com.metehan.app.ws.shared;

import com.metehan.app.ws.data.model.entity.CityEntity;
import com.metehan.app.ws.data.model.entity.ProvinceEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	
	private CityEntity city;
	
	private ProvinceEntity province;
	
	private String addressId;

}
