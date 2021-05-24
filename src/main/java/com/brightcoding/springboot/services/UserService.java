package com.brightcoding.springboot.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.brightcoding.springboot.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	public UserDto createUser(UserDto userDto);
	
	public UserDto getUserByEmail(String email); 
	
	public UserDto getUserId(String userId);
	
	public UserDto update(String userId, UserDto userDto);
	
	public void delete(String userId);
	
//	public List<UserDto> allUsers();

	public List<UserDto> getUsers(int page, int limit, String search, int status);
	
}
