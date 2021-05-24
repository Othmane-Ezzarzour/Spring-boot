package com.brightcoding.springboot.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brightcoding.springboot.exceptions.UserException;
import com.brightcoding.springboot.requests.UserRequest;
import com.brightcoding.springboot.responses.ErrorMessages;
import com.brightcoding.springboot.responses.UserResponse;
import com.brightcoding.springboot.services.UserService;
import com.brightcoding.springboot.shared.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		UserDto userDto = userService.getUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
	
	@PostMapping(path="/create", consumes = {MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
		if(userRequest.getFirstName().isEmpty()) {
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);

		UserDto createUser = userService.createUser(userDto);

		UserResponse userResponse =modelMapper.map(createUser, UserResponse.class);
		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path="/update/{userId}",consumes = {MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto update = userService.update(userId, userDto);
		UserResponse userResponse = modelMapper.map(update, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path="/delete/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable String userId) {
		userService.delete(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path="/getUsers", produces = {MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public List<UserResponse> getUsers(@RequestParam(value="page", defaultValue = "1")int page,
			@RequestParam(value="limit", defaultValue = "15") int limit, @RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "status", defaultValue = "1") int status) {
		List<UserDto> userDto = userService.getUsers(page, limit, search, status);
		List<UserResponse> userResponse = new ArrayList<>();
		for(UserDto users: userDto) {
			ModelMapper modepMpper = new ModelMapper();
			UserResponse user = modepMpper.map(users, UserResponse.class);
			userResponse.add(user);
		}
		return userResponse;
	}
	
//	@GetMapping("/allUsers")
//	public List<UserResponse> allUsers() {
//		List<UserDto> userDto = userService.allUsers();
//		List<UserResponse> userResponse = new ArrayList<>();
//		for(UserDto users: userDto) {
//			UserResponse user = new UserResponse();
//			BeanUtils.copyProperties(users, user);
//			userResponse.add(user);
//		}
//		return userResponse;
//	}
	
}
