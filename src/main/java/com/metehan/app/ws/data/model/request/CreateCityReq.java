package com.metehan.app.ws.data.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCityReq {
	
	@NotNull(message="Content cannot be null")
	@Size(max=20)
	private String cityName;

}
