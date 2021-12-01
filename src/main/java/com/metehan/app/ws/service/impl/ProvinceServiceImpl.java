package com.metehan.app.ws.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.ProvinceRepository;
import com.metehan.app.ws.data.model.entity.ProvinceEntity;
import com.metehan.app.ws.service.ProvinceService;
import com.metehan.app.ws.shared.ProvinceDto;

@Service
public class ProvinceServiceImpl implements ProvinceService {
	
	private final ProvinceRepository provinceRepository;
	
	public ProvinceServiceImpl(ProvinceRepository provinceRepository) {
		this.provinceRepository = provinceRepository;
	}

	@Override
	public ProvinceDto createProvince(ProvinceDto provinceDetails) {
		
	    provinceDetails.setProvinceId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		ProvinceEntity province = modelMapper.map(provinceDetails, ProvinceEntity.class);
		provinceRepository.save(province);
		
		ProvinceDto returnValue = modelMapper.map(province, ProvinceDto.class);
		return returnValue;
	}

	@Override
	public ProvinceDto getProvinceByName(String provinceName) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		ProvinceEntity province = provinceRepository.findByProvinceName(provinceName);
		if(province==null) {
			return null;
		}
		ProvinceDto returnValue = modelMapper.map(province, ProvinceDto.class);
		return returnValue;
	}

}
