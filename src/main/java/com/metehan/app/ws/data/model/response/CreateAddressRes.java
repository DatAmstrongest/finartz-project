package com.metehan.app.ws.data.model.response;

import com.metehan.app.ws.data.model.entity.CityEntity;
import com.metehan.app.ws.data.model.entity.ProvinceEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressRes {
	
	private ProvinceEntity province;
	
	private CityEntity city;
	
	private String addressId;

}
