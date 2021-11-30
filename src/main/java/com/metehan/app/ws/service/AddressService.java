package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.AddressDto;

public interface AddressService {
	
	AddressDto createAddress(String cityId, String addressId);
	
	AddressDto getAddress(String addressId);

}
