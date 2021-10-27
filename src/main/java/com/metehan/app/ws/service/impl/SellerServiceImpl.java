package com.metehan.app.ws.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.SellerEntity;
import com.metehan.app.ws.data.SellerRepository;
import com.metehan.app.ws.data.UserEntity;
import com.metehan.app.ws.data.UsersRepository;
import com.metehan.app.ws.service.SellerService;
import com.metehan.app.ws.service.UsersService;
import com.metehan.app.ws.shared.SellerDto;
import com.metehan.app.ws.shared.UserDto;

@Service
public class SellerServiceImpl implements SellerService {
	
	SellerRepository sellerRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public SellerServiceImpl(SellerRepository sellerRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		this.sellerRepository = sellerRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public SellerDto createSeller(SellerDto sellerDetails) {
		sellerDetails.setRestaurantId(UUID.randomUUID().toString());
		sellerDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(sellerDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		SellerEntity sellerEntity = modelMapper.map(sellerDetails, SellerEntity.class);
		
		sellerRepository.save(sellerEntity);
		
		SellerDto  returnValue = modelMapper.map(sellerEntity, SellerDto.class);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String restaurantname) throws UsernameNotFoundException {
		SellerEntity sellerEntity = sellerRepository.findByEmail(restaurantname);		
		
		if(sellerEntity == null) throw new UsernameNotFoundException(restaurantname);
		return new User(sellerEntity.getEmail(), sellerEntity.getEncryptedPassword(), true, true ,true ,true, new ArrayList<>() );
	}

	@Override
	public SellerDto getSellerDetailsByEmail(String email) {
		
		SellerEntity sellerEntity = sellerRepository.findByEmail(email);		
		
		if(sellerEntity == null) throw new UsernameNotFoundException(email);
		return new ModelMapper().map(sellerEntity, SellerDto.class);
	}
	
	
	
}
