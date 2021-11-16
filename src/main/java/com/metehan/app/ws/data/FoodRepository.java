package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;

import com.metehan.app.ws.data.model.entity.CommentEntity;
import com.metehan.app.ws.data.model.entity.FoodEntity;

public interface FoodRepository extends CrudRepository<FoodEntity,Long> {
	FoodEntity findByFoodId(String foodId);

}
