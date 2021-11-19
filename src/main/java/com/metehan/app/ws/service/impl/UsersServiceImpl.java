package com.metehan.app.ws.service.impl;
import java.util.ArrayList;
import java.util.List;

import com.metehan.app.ws.data.model.entity.UserEntity.Role;
import com.metehan.app.ws.data.model.request.UserLogin;

import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.UsersRepository;
import com.metehan.app.ws.data.model.entity.FoodEntity;
import com.metehan.app.ws.data.model.entity.UserEntity;
import com.metehan.app.ws.service.UsersService;
import com.metehan.app.ws.shared.FoodDto;
import com.metehan.app.ws.shared.RestaurantDto;
import com.metehan.app.ws.shared.UserDto;


@Service
public class UsersServiceImpl implements UsersService {
	
	UsersRepository usersRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		userDetails.setUserRole(Role.USER);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		
		usersRepository.save(userEntity);
		
		UserDto  returnValue = modelMapper.map(userEntity, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = usersRepository.findByEmail(username);		
		
		if(userEntity == null) throw new UsernameNotFoundException(username);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true ,true ,true, new ArrayList<>() );
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		
		UserEntity userEntity = usersRepository.findByEmail(email);		
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		return new ModelMapper().map(userEntity, UserDto.class);
	}
	
	@Override
	public UserDto login(UserDto userDetails) {
		
		
		String userEmail = userDetails.getEmail();
		UserEntity user = usersRepository.findByEmail(userEmail);
		if(bCryptPasswordEncoder.matches(userDetails.getPassword(), user.getEncryptedPassword())) {
			return new ModelMapper().map(user, UserDto.class);
		}
		
		return null;
	}
    
	@Override
	public UserDto getUser(String userId) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity usersIterable = usersRepository.findByUserId(userId);
		UserDto   returnValue = modelMapper.map(usersIterable, UserDto.class);
		
		return returnValue;
		
		
	}
	
	@Override
	public UserDto[] getUsers() {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Iterable<UserEntity> usersIterable = usersRepository.findAll();
		UserDto []  returnValue = modelMapper.map(usersIterable, UserDto[].class);
		
		return returnValue;
		
		
	}

	@Override
	public boolean deleteUser(String userId) {
		
		UserEntity user = usersRepository.findByUserId(userId);
		
		if(user!=null) {
			
			usersRepository.delete(user);
			return true;
			
		}
		else {
			return false;
			
		}
	}

	@Override
	public UserDto updateUser(UserDto userDetails, String userId) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity = usersRepository.findByUserId(userId);
		
		if(userEntity==null) {
			return null;
		}
		if(userDetails.getLastName()!=null) {
			
			userEntity.setLastName(userDetails.getLastName());
			
		}
		if (userDetails.getFirstName()!=null) {
			
			userEntity.setFirstName(userDetails.getFirstName());
			
		}
		
		if (userDetails.getAddress()!=null) {
			userEntity.setAddress(userDetails.getAddress());
			
			
		}
		
		if(userDetails.getUserRole()!=null) {
			
			userEntity.setUserRole(userDetails.getUserRole());

			
		}
		if(userEntity!=null) {
			usersRepository.save(userEntity);
			
			UserDto  returnValue = modelMapper.map(userEntity, UserDto.class);
			return returnValue;
			
		}
		else {
			return null;
		}
		
	}
	


	

	
	
	
	
	
}
