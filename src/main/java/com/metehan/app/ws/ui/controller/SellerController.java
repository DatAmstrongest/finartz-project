package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.service.SellerService;
import com.metehan.app.ws.service.UsersService;
import com.metehan.app.ws.shared.SellerDto;
import com.metehan.app.ws.shared.UserDto;
import com.metehan.app.ws.ui.model.CreateSellerRequestModel;
import com.metehan.app.ws.ui.model.CreateSellerResponseModel;
import com.metehan.app.ws.ui.model.CreateUserRequestModel;
import com.metehan.app.ws.ui.model.CreateUserResponseModel;

@RestController
@RequestMapping("sellers")
public class SellerController {
	
	@Autowired
	SellerService sellerService;
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			
			)
	public ResponseEntity<CreateSellerResponseModel> createUser(@Valid @RequestBody CreateSellerRequestModel sellerDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		SellerDto sellerDto = modelMapper.map(sellerDetails, SellerDto.class);
		
		SellerDto createdSeller = sellerService.createSeller(sellerDto);
		
		CreateSellerResponseModel returnValue = modelMapper.map(createdSeller, CreateSellerResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@PutMapping
	public String updateSeller()
	{
		return "update seller was called";
		
	}
	
	public String deleteSeller() {
		return "delete seller was called";
	}
	

}
