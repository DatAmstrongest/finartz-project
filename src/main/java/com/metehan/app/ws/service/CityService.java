package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.CityDto;

public interface CityService {
	
	CityDto createCity(CityDto cityDetails);
	
	CityDto getCityByName(String cityName);

}
