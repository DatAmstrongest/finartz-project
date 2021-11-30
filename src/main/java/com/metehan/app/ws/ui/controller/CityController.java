package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateCityReq;
import com.metehan.app.ws.data.model.response.CreateCityRes;
import com.metehan.app.ws.service.CityService;
import com.metehan.app.ws.shared.CityDto;

@RestController
@RequestMapping("/city")
public class CityController {
	
private final CityService cityService;
	
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateCityRes> createCity(@Valid @RequestBody CreateCityReq cityDetails) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		CityDto cityDto = modelMapper.map(cityDetails, CityDto.class);
		CityDto createdCity = cityService.createCity(cityDto);

		if (createdCity == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);

		}

		CreateCityRes returnValue = modelMapper.map(createdCity, CreateCityRes.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

}
