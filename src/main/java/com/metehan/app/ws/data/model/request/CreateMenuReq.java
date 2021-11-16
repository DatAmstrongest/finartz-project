package com.metehan.app.ws.data.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateMenuReq {
	
	@NotNull(message="Restaurant name cannot be null")
	@Size(min=2, max=100, message="Restaurant name must not be less than two characters")
	private String menuName;
	
	
	private String menuId;


	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public String getMenuId() {
		return menuId;
	}


	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	


}
