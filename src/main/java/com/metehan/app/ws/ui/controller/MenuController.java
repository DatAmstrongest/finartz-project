package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateCommentReq;
import com.metehan.app.ws.data.model.request.CreateMenuReq;
import com.metehan.app.ws.data.model.response.CreateCommentRes;
import com.metehan.app.ws.data.model.response.CreateMenuRes;
import com.metehan.app.ws.data.model.response.CreateRestaurantRes;
import com.metehan.app.ws.service.CommentService;
import com.metehan.app.ws.service.MenuService;
import com.metehan.app.ws.shared.CommentDto;
import com.metehan.app.ws.shared.MenuDto;
import com.metehan.app.ws.shared.RestaurantDto;

@SpringBootApplication
@RestController
@RequestMapping(value={"restaurant/{restaurantId}/menu","/menu"})
public class MenuController {
	@Autowired
	MenuService menuService;
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			
			)
	public ResponseEntity<CreateMenuRes> createMenu(@PathVariable("restaurantId") String restaurantId , @Valid @RequestBody CreateMenuReq menuDetails ) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		MenuDto menuDto = modelMapper.map(menuDetails, MenuDto.class);
		MenuDto createdMenu = menuService.createMenu(menuDto, restaurantId);
		
		if(createdMenu==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
			
		}
		
		CreateMenuRes returnValue = modelMapper.map(createdMenu, CreateMenuRes.class);
		returnValue.setRestaurantId(restaurantId);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@GetMapping(
			path = "{menuId}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<CreateMenuRes> getMenu(@PathVariable(name="menuId", required=false) String menuId, @PathVariable(name="restaurantId") String restaurantId )
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		MenuDto menu;
		
		if(menuId!=null) {
		   menu = menuService.getMenuById(menuId);
			
		}
		else {
			menu = menuService.getMenuByRestaurantId(restaurantId);
		}
		
		CreateMenuRes returnValue = modelMapper.map(menu, CreateMenuRes.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}
	
	@DeleteMapping(
			path="/{menuId}"
			)
	public String deleteMenu(@PathVariable("menuId") String menuId) {
		
		if(menuService.deleteMenu(menuId)) {
			return "Menu is delete with id "+menuId;
		}
		
		return "Unsuccessful operation";
	}
	
	
	
	
	

	

	
	

}
