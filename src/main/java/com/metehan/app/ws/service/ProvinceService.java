package com.metehan.app.ws.service;

import com.metehan.app.ws.shared.ProvinceDto;

public interface ProvinceService {
	
	ProvinceDto createProvince (ProvinceDto provinceDetails);
	
	ProvinceDto getProvinceByName(String provinceName);

}
