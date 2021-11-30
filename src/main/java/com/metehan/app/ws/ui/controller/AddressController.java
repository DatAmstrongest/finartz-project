package com.metehan.app.ws.ui.controller;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.response.CreateAddressRes;
import com.metehan.app.ws.data.model.response.CreateUserRes;
import com.metehan.app.ws.service.AddressService;
import com.metehan.app.ws.shared.AddressDto;
import com.metehan.app.ws.shared.UserDto;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	private final AddressService addressService;
	
	public AddressController (AddressService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping()
	public ResponseEntity<CreateAddressRes> createAddress(@RequestParam("city-id") String cityId, @RequestParam("province-id") String provinceId){

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		AddressDto createdAddress = addressService.createAddress(cityId, provinceId);
		
		if(createdAddress==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		}
		
		CreateAddressRes returnValue = modelMapper.map(createdAddress, CreateAddressRes.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@GetMapping(path = "/{address-id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateAddressRes> getAddress(@PathVariable("address-id") String addressId){
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		AddressDto address = addressService.getAddress(addressId);

		CreateAddressRes returnValue = modelMapper.map(address, CreateAddressRes.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}

}
