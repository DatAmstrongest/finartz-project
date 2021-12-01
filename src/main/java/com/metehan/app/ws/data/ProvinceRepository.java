package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;

import com.metehan.app.ws.data.model.entity.ProvinceEntity;

public interface ProvinceRepository  extends CrudRepository<ProvinceEntity, Long>{

	ProvinceEntity findByProvinceId(String provinceId);
	
	ProvinceEntity findByProvinceName(String provinceName);
}
