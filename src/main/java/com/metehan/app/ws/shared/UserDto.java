package com.metehan.app.ws.shared;

import java.io.Serializable;

import com.metehan.app.ws.data.model.entity.UserEntity.Role;


public class UserDto implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -5619898394497158418L;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String password;
	private String userId;
	private String encryptedPassword;
	private Role  userRole;
	
	public Role getUserRole() {
		return userRole;
	}
	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	
	

}
