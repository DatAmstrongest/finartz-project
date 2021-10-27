package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<SellerEntity, Long> {
	
	SellerEntity findByEmail(String email);
}
