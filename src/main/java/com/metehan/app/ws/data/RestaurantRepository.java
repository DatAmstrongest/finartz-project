package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;

import com.metehan.app.ws.data.model.entity.RestaurantEntity;


public interface RestaurantRepository extends CrudRepository<RestaurantEntity, Long> {

}
