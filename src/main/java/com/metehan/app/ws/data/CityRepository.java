package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;

import com.metehan.app.ws.data.model.entity.CityEntity;

public interface CityRepository extends CrudRepository<CityEntity, Long> {
	
	CityEntity findByCityId(String cityId);
	CityEntity findByCityName(String cityName);

}
