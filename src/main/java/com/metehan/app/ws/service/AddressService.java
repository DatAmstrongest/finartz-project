package com.metehan.app.ws.service;

import com.metehan.app.ws.data.model.request.CreateAddressReq;
import com.metehan.app.ws.shared.AddressDto;

public interface AddressService {
	
	AddressDto createAddress(CreateAddressReq addressDetails);
	
	AddressDto getAddress(String addressId);

}
