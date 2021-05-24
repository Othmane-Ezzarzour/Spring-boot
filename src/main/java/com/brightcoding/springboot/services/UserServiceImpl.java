package com.brightcoding.springboot.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brightcoding.springboot.modeles.Users;
import com.brightcoding.springboot.repository.UserRepository;
import com.brightcoding.springboot.shared.Utils;
import com.brightcoding.springboot.shared.dto.AddresseDto;
import com.brightcoding.springboot.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Utils util;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		if(userRepository.findByEmail(userDto.getEmail()) != null){
			throw new RuntimeException("Email dupliquer");
		}
		
		for (int i = 0; i < userDto.getAddresses().size(); i++) {
			AddresseDto addresseDto =userDto.getAddresses().get(i);
			addresseDto.setAddressId(util.generateStringId(30));
			addresseDto.setUser(userDto);
			userDto.getAddresses().set(i, addresseDto);
		}
		
//		ContactDto contactDto = userDto.getContact();
//		contactDto.setContactId(util.generateStringId(30));
//		contactDto.setUser(userDto);
		
		userDto.getContact().setContactId(util.generateStringId(30));
		userDto.getContact().setUser(userDto);
		
		ModelMapper modelMapper = new ModelMapper();
		Users users = modelMapper.map(userDto, Users.class);
	
		users.setEmailVerificationStatus(false);
		users.setUserId(util.generateStringId(30));
		users.setEncryptePassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		
		Users usersSave = userRepository.save(users);
		UserDto userDtoSave = modelMapper.map(usersSave, UserDto.class);
		
		return userDtoSave;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(user.getEmail(), user.getEncryptePassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByEmail(String email) {
		Users userId = userRepository.findByEmail(email);
		if(userId == null) {
			throw new UsernameNotFoundException(email);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userId, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto getUserId(String userId) {
		Users user = userRepository.findByUserId(userId);
		if(user == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto update(String userId, UserDto userDto) {
		Users user = userRepository.findByUserId(userId);
		if(user == null) {
			throw new UsernameNotFoundException(userId);
		}
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
//		user.setEmail(userDto.getEmail());
//		user.setEncryptePassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		
//		for (int i = 0; i < userDto.getAddresses().size(); i++) {
//			AddresseDto addresseDto =userDto.getAddresses().get(i);
//			addresseDto.setAddressId(util.generateStringId(30));
//			addresseDto.setUser(userDto);
//			userDto.getAddresses().set(i, addresseDto);
//		}
//		user.getContact().setContactId(util.generateStringId(30));
//		user.getContact().setUser(user);

		
		
		Users userUpdate =userRepository.save(user);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDtoUpdate = modelMapper.map(userUpdate, UserDto.class);
		
		
		return userDtoUpdate;
	}

	@Override
	public void delete(String userId) {
		Users user = userRepository.findByUserId(userId);
		if(user == null) {
			throw new UsernameNotFoundException(userId);
		}
		userRepository.delete(user);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit, String search, int status) {
		if(page > 0) {
			page -=1;
		}
		Pageable pageable = PageRequest.of(page, limit);
		Page<Users> pageRequest;
		if(search.isEmpty()) {
		    pageRequest = userRepository.findAll(pageable);
		}else {
			pageRequest = userRepository.findAllUsersByCriteria(pageable, search, status);
		}
		
		List<Users> users = pageRequest.getContent();
		List<UserDto> usersDto = new ArrayList<>();
		
		for(Users user: users) {
			ModelMapper modelMapper = new ModelMapper();
			UserDto userDto1 = modelMapper.map(user, UserDto.class);
			usersDto.add(userDto1);
		}
		return usersDto;
	}
	
//	@Override
//	public List<UserDto> allUsers() {
//		List<Users> user = userRepository.findAll();
//		List<UserDto> userDto = new ArrayList<>();
//
//		for(Users users: user) {
//			UserDto userDto2 = new UserDto();
//			BeanUtils.copyProperties(users, userDto2);
//			userDto.add(userDto2);
//		}
//		return userDto;
//	}

}
