package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;

import com.metehan.app.ws.data.model.entity.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

	AddressEntity findByAddressId(String addressId);
	
}
