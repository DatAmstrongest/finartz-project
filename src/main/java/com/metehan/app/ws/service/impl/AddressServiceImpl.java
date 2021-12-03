package com.metehan.app.ws.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.AddressRepository;
import com.metehan.app.ws.data.CityRepository;
import com.metehan.app.ws.data.ProvinceRepository;
import com.metehan.app.ws.data.model.entity.AddressEntity;
import com.metehan.app.ws.data.model.entity.CityEntity;
import com.metehan.app.ws.data.model.entity.ProvinceEntity;
import com.metehan.app.ws.data.model.request.CreateAddressReq;
import com.metehan.app.ws.service.AddressService;
import com.metehan.app.ws.service.CityService;
import com.metehan.app.ws.service.ProvinceService;
import com.metehan.app.ws.shared.AddressDto;
import com.metehan.app.ws.shared.CityDto;
import com.metehan.app.ws.shared.ProvinceDto;

@Service
public class AddressServiceImpl implements AddressService {
	
	private final AddressRepository addressRepository;
	private final CityRepository cityRepository;
	private final ProvinceRepository provinceRepository;
	
	private final CityService cityService;
	private final ProvinceService provinceService;
	
	
	public AddressServiceImpl(AddressRepository addressRepository, CityRepository cityRepository, ProvinceRepository provinceRepository, CityService cityService, ProvinceService provinceService) {
		this.addressRepository = addressRepository;
		this.cityRepository = cityRepository;
		this.provinceRepository = provinceRepository;
		
		this.provinceService = provinceService;
		this.cityService = cityService;
	}

	@Override
	public AddressDto createAddress(CreateAddressReq addressDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AddressEntity address = new AddressEntity();
		address.setAddressId(UUID.randomUUID().toString());
		
		CityEntity city = cityRepository.findByCityName(addressDetails.getCity().getCityName());
		ProvinceEntity province = provinceRepository.findByProvinceName(addressDetails.getProvince().getProvinceName());
		
		if(city == null) {
					
			CityDto cityDto = new CityDto();
			cityDto.setCityName(addressDetails.getCity().getCityName());
			cityService.createCity(cityDto);
				
		}
		
		if(province == null) {
			
			ProvinceDto provinceDto = new ProvinceDto();
			provinceDto.setProvinceName(addressDetails.getProvince().getProvinceName());
			provinceService.createProvince(provinceDto);
		}
		
		city = cityRepository.findByCityName(addressDetails.getCity().getCityName());
		province = provinceRepository.findByProvinceName(addressDetails.getProvince().getProvinceName());
		
		address.setCity(city);
		address.setProvince(province);
		addressRepository.save(address);
		
		AddressDto returnValue = modelMapper.map(address, AddressDto.class);
		return returnValue;
		
	}

	@Override
	public AddressDto getAddress(String addressId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		AddressEntity  address = addressRepository.findByAddressId(addressId);

		AddressDto returnValue = modelMapper.map(address, AddressDto.class);
		return returnValue;
	}

}
