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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateRestaurantReq;

import com.metehan.app.ws.data.model.request.UpdateRestaurantReq;
import com.metehan.app.ws.data.model.response.CreateRestaurantRes;

import com.metehan.app.ws.data.model.response.UpdateRestaurantRes;
import com.metehan.app.ws.service.AddressService;
import com.metehan.app.ws.service.CityService;
import com.metehan.app.ws.service.ProvinceService;
import com.metehan.app.ws.service.RestaurantService;
import com.metehan.app.ws.shared.AddressDto;
import com.metehan.app.ws.shared.CityDto;
import com.metehan.app.ws.shared.ProvinceDto;
import com.metehan.app.ws.shared.RestaurantDto;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {

	private final RestaurantService restaurantService;
	private final CityService cityService;
	private final ProvinceService provinceService;
	private final AddressService addressService;
	
	public RestaurantController(RestaurantService restaurantService, CityService cityService, ProvinceService provinceService, AddressService addressService) {
		this.restaurantService = restaurantService;
		this.addressService = addressService;
		this.cityService = cityService;
		this.provinceService = provinceService;
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateRestaurantRes[]> getRestaurantsOfUser(@RequestParam("user-id") String userId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		RestaurantDto[] createdRestaurant = restaurantService.getRestaurantsOfUser(userId);

		CreateRestaurantRes[] returnValue = modelMapper.map(createdRestaurant, CreateRestaurantRes[].class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

	}
	
	@GetMapping(path="/city",produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateRestaurantRes[]> getCloseCityRestaurants(@RequestParam("user-id") String userId){
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		RestaurantDto[] createdRestaurant = restaurantService.getCloseCityRestaurants(userId);

		CreateRestaurantRes[] returnValue = modelMapper.map(createdRestaurant, CreateRestaurantRes[].class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}
	
	@GetMapping(path="/province",produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateRestaurantRes[]> getCloseProvinceRestaurants(@RequestParam("user-id") String userId){
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		RestaurantDto[] createdRestaurant = restaurantService.getCloseProvinceRestaurants(userId);

		CreateRestaurantRes[] returnValue = modelMapper.map(createdRestaurant, CreateRestaurantRes[].class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateRestaurantRes> createRestaurant(@RequestParam("user-id") String userId,
			@Valid @RequestBody CreateRestaurantReq restaurantDetails) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		CityDto cityDto = cityService.getCityByName(restaurantDetails.getCityName());
		if(cityDto == null) {
			cityDto = new CityDto();
			cityDto.setCityName(restaurantDetails.getCityName());
			cityDto = cityService.createCity(cityDto);
		}
		
		ProvinceDto provinceDto = provinceService.getProvinceByName(restaurantDetails.getProvinceName());
		if(provinceDto == null) {
			provinceDto = new ProvinceDto();
			provinceDto.setProvinceName(restaurantDetails.getProvinceName());
			provinceDto = provinceService.createProvince(provinceDto);
		}
		
		AddressDto createdAddress = addressService.createAddress(cityDto.getCityId(), provinceDto.getProvinceId());
		
		RestaurantDto restaurantDto = modelMapper.map(restaurantDetails, RestaurantDto.class);
		RestaurantDto createdRestaurant = restaurantService.createRestaurant(restaurantDto, userId, createdAddress.getAddressId());

		if (createdRestaurant == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		}

		CreateRestaurantRes returnValue = modelMapper.map(createdRestaurant, CreateRestaurantRes.class);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);

	}

	@PutMapping(path = "/{restaurant-id}")
	public ResponseEntity<UpdateRestaurantRes> updateRestaurant(@RequestParam("user-id") String userId,
			@PathVariable("restaurant-id") String restaurantId,
			@Valid @RequestBody UpdateRestaurantReq restaurantDetails) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		RestaurantDto restaurantDto = modelMapper.map(restaurantDetails, RestaurantDto.class);
		RestaurantDto createdRestaurant = restaurantService.updateRestaurant(restaurantDto, userId, restaurantId);

		if (createdRestaurant == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		UpdateRestaurantRes returnValue = modelMapper.map(createdRestaurant, UpdateRestaurantRes.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

	}

	@DeleteMapping()
	public String deleteAllRestaurantsofUser(@RequestParam("user-id") String userId) {
		if (restaurantService.deleteAllRestaurantsOfUser(userId)) {
			return "All restaurants of  user with id " + userId + " deleted";
		}
		return "Unsuccessful operation";
	}

	@DeleteMapping(path = "/{restaurant-id}")
	public String deleteRestaurant(@RequestParam("user-id") String userId,
			@PathVariable("restaurant-id") String restaurantId) {

		if (restaurantService.deleteRestaurant(userId, restaurantId)) {
			return "Restaurant with id " + restaurantId + " deleted for user with id " + userId;
		}

		return "Unsuccessful operation";
	}

}
