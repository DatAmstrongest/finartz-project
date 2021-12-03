package com.metehan.app.ws.data.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressReq {
	
	@NotNull(message="City cannot be null")
	private CreateCityReq city;
	
	@NotNull(message="Province cannot be null")
	private CreateProvinceReq province;
}
