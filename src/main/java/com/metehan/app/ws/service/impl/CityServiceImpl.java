package com.metehan.app.ws.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.CityRepository;
import com.metehan.app.ws.data.model.entity.CityEntity;
import com.metehan.app.ws.service.CityService;
import com.metehan.app.ws.shared.CityDto;

@Service
public class CityServiceImpl implements CityService {
	
	private final CityRepository cityRepository;
	
	public CityServiceImpl(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	public CityDto createCity(CityDto cityDetails) {
		
		cityDetails.setCityId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		CityEntity city = modelMapper.map(cityDetails, CityEntity.class);
		cityRepository.save(city);
		
		CityDto returnValue = modelMapper.map(city, CityDto.class);
		return returnValue;
	}

	@Override
	public CityDto getCityByName(String cityName) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		CityEntity city = cityRepository.findByCityName(cityName);
		if(city==null) {
			return null;
		}
		CityDto returnValue = modelMapper.map(city, CityDto.class);
		return returnValue;
		
	}	

}
