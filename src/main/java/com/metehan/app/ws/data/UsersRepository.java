package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;
import com.metehan.app.ws.data.model.entity.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String userId);
}
