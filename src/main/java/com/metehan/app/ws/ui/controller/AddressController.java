package com.metehan.app.ws.ui.controller;


import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateAddressReq;
import com.metehan.app.ws.data.model.response.CreateAddressRes;
import com.metehan.app.ws.service.AddressService;
import com.metehan.app.ws.shared.AddressDto;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	private final AddressService addressService;
	
	public AddressController (AddressService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateAddressRes> createAddress(@Valid @RequestBody CreateAddressReq addressDetails){

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		AddressDto createdAddress = addressService.createAddress(addressDetails);
		
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
