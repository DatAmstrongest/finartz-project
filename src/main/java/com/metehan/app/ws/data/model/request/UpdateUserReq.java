package com.metehan.app.ws.data.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.metehan.app.ws.data.model.entity.UserEntity.Role;

public class UpdateUserReq {
	
	@Size(min=2, message="First name must not be less than two characters")
	private String firstName;

	@Size(min=2, message="Last name must not be less than two characters")
	private String lastName;
	
	
	@Email
	private String email;
	
	private String address;
	

	private Role userRole;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
	
	
	

}
