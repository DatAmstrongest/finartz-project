package com.metehan.app.ws.service.impl;

import java.util.ArrayList;

import com.metehan.app.ws.data.model.entity.UserEntity.Role;

import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.metehan.app.ws.data.AddressRepository;
import com.metehan.app.ws.data.UsersRepository;
import com.metehan.app.ws.data.model.entity.AddressEntity;
import com.metehan.app.ws.data.model.entity.UserEntity;
import com.metehan.app.ws.service.UsersService;
import com.metehan.app.ws.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService {

	private final UsersRepository usersRepository;
	private final AddressRepository addressRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AddressRepository addressRepository) {
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.addressRepository = addressRepository;
	}

	@Override
	public UserDto createUser(UserDto userDetails, String addressId) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AddressEntity address = addressRepository.findByAddressId(addressId);
		
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		userDetails.setUserRole(Role.USER);
		userDetails.setAddress(address);

		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

		usersRepository.save(userEntity);

		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = usersRepository.findByEmail(username);

		if (userEntity == null)
			throw new UsernameNotFoundException(username);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
				new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {

		UserEntity userEntity = usersRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto login(UserDto userDetails) {

		String userEmail = userDetails.getEmail();
		UserEntity user = usersRepository.findByEmail(userEmail);
		if (bCryptPasswordEncoder.matches(userDetails.getPassword(), user.getEncryptedPassword())) {
			return new ModelMapper().map(user, UserDto.class);
		}

		return null;
	}

	@Override
	public UserDto getUser(String userId) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity usersIterable = usersRepository.findByUserId(userId);
		UserDto returnValue = modelMapper.map(usersIterable, UserDto.class);

		return returnValue;

	}

	@Override
	public UserDto[] getUsers() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Iterable<UserEntity> usersIterable = usersRepository.findAll();
		UserDto[] returnValue = modelMapper.map(usersIterable, UserDto[].class);

		return returnValue;

	}

	@Override
	public boolean deleteUser(String userId) {

		UserEntity user = usersRepository.findByUserId(userId);

		if (user != null) {

			usersRepository.delete(user);
			return true;

		} else {
			return false;

		}
	}


}
