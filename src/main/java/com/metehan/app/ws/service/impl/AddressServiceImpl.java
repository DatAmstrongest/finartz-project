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
import com.metehan.app.ws.service.AddressService;
import com.metehan.app.ws.shared.AddressDto;

@Service
public class AddressServiceImpl implements AddressService {
	
	private final AddressRepository addressRepository;
	private final CityRepository cityRepository;
	private final ProvinceRepository provinceRepository;
	
	
	public AddressServiceImpl(AddressRepository addressRepository, CityRepository cityRepository, ProvinceRepository provinceRepository) {
		this.addressRepository = addressRepository;
		this.cityRepository = cityRepository;
		this.provinceRepository = provinceRepository;
	}

	@Override
	public AddressDto createAddress(String cityId, String addressId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AddressEntity address = new AddressEntity();
		
		address.setAddressId(UUID.randomUUID().toString());
		
		CityEntity city = cityRepository.findByCityId(cityId);
		ProvinceEntity province = provinceRepository.findByProvinceId(addressId);
		
		if(city == null || province == null ) {
			return null;
		}
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
