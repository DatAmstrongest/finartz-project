package com.metehan.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.metehan.app.ws.shared.SellerDto;

public interface SellerService extends UserDetailsService {
	
	SellerDto createSeller(SellerDto userDetails);
	SellerDto getSellerDetailsByEmail(String email);

}
