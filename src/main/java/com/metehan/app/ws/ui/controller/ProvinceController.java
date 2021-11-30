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

import com.metehan.app.ws.data.model.request.CreateProvinceReq;
import com.metehan.app.ws.data.model.response.CreateProvinceRes;
import com.metehan.app.ws.service.ProvinceService;
import com.metehan.app.ws.shared.ProvinceDto;

@RestController
@RequestMapping("/province")
public class ProvinceController {
	
	final ProvinceService provinceService;
	
	public ProvinceController(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateProvinceRes> createProvince(@Valid @RequestBody CreateProvinceReq provinceDetails) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		ProvinceDto provinceDto = modelMapper.map(provinceDetails, ProvinceDto.class);
		ProvinceDto createdProvince = provinceService.createProvince(provinceDto);

		if (createdProvince == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);

		}

		CreateProvinceRes returnValue = modelMapper.map(createdProvince, CreateProvinceRes.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

}
