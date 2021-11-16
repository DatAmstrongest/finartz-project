package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateCommentReq;
import com.metehan.app.ws.data.model.request.CreateMenuReq;
import com.metehan.app.ws.data.model.response.CreateCommentRes;
import com.metehan.app.ws.data.model.response.CreateMenuRes;
import com.metehan.app.ws.service.CommentService;
import com.metehan.app.ws.service.MenuService;
import com.metehan.app.ws.shared.CommentDto;
import com.metehan.app.ws.shared.MenuDto;

@SpringBootApplication
@RestController
@RequestMapping(value={"/{restaurantName}/menu","/menu"})
public class MenuController {
	@Autowired
	MenuService menuService;
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			
			)
	public ResponseEntity<CreateMenuRes> createMenu(@PathVariable("restaurantName") String restaurantName , @Valid @RequestBody CreateMenuReq menuDetails ) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		MenuDto menuDto = modelMapper.map(menuDetails, MenuDto.class);
		MenuDto createdMenu = menuService.createMenu(menuDto, restaurantName);
		
		if(createdMenu==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
			
		}
		
		CreateMenuRes returnValue = modelMapper.map(createdMenu, CreateMenuRes.class);
		returnValue.setRestaurantName(restaurantName);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	

	

	
	

}
