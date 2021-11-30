package com.metehan.app.ws.data.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {

	private String email;
	private String password;

}
