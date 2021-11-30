package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateMenuReq;
import com.metehan.app.ws.data.model.response.CreateMenuRes;

import com.metehan.app.ws.service.MenuService;
import com.metehan.app.ws.shared.MenuDto;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {

	private final MenuService menuService;
	
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateMenuRes> createMenu(@RequestParam("restaurant-id") String restaurantId,
			@Valid @RequestBody CreateMenuReq menuDetails) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		MenuDto menuDto = modelMapper.map(menuDetails, MenuDto.class);
		MenuDto createdMenu = menuService.createMenu(menuDto, restaurantId);

		if (createdMenu == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);

		}

		CreateMenuRes returnValue = modelMapper.map(createdMenu, CreateMenuRes.class);
		returnValue.setRestaurantId(restaurantId);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

	@GetMapping(path = "/{menu-id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateMenuRes> getMenu(@PathVariable(name = "menu-id") String menuId,
			@RequestParam(name = "restaurant-id") String restaurantId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		MenuDto menu;
		menu = menuService.getMenuById(menuId);
		CreateMenuRes returnValue = modelMapper.map(menu, CreateMenuRes.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

	}

	@DeleteMapping(path = "/{menuId}")
	public String deleteMenu(@PathVariable("menuId") String menuId) {

		if (menuService.deleteMenu(menuId)) {
			return "Menu is delete with id " + menuId;
		}

		return "Unsuccessful operation";
	}

}
