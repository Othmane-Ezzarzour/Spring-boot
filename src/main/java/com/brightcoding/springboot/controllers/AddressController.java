package com.brightcoding.springboot.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
import org.springframework.web.bind.annotation.RestController;

import com.brightcoding.springboot.requests.AddresseRequest;
import com.brightcoding.springboot.responses.AddresseResponse;
import com.brightcoding.springboot.services.AddressService;
import com.brightcoding.springboot.shared.dto.AddresseDto;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/all-addresses")
	public ResponseEntity<List<AddresseResponse>> getAddresses(Principal principal) {
		List<AddresseDto> addresseDto = addressService.getAddresses(principal.getName());
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<AddresseResponse>>() {}.getType();
		List<AddresseResponse> addresseResponse = modelMapper.map(addresseDto, listType);
		return new ResponseEntity<List<AddresseResponse>>(addresseResponse, HttpStatus.OK);
	}
	
	@PostMapping(path="/create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<AddresseResponse> saveAddrese(@RequestBody AddresseRequest addresseRequest, Principal principal){
		
		ModelMapper modelMapper = new ModelMapper();
		AddresseDto addresesDto = modelMapper.map(addresseRequest, AddresseDto.class);
		AddresseDto saveAddresse = addressService.createAddresse(addresesDto, principal.getName());
		
		AddresseResponse addresseResponse = modelMapper.map(saveAddresse, AddresseResponse.class);
		return new ResponseEntity<AddresseResponse>(addresseResponse, HttpStatus.CREATED);
	}

	@GetMapping(path = "/oneAddress/{id}")
	public ResponseEntity<AddresseResponse> getOneAddress(@PathVariable(name = "id") String addressId){
		AddresseDto addresseDto = addressService.getOneAddress(addressId);
		ModelMapper modelMapper = new ModelMapper();
		
		AddresseResponse addresseResponse = modelMapper.map(addresseDto, AddresseResponse.class);
		
		return new ResponseEntity<AddresseResponse>(addresseResponse, HttpStatus.OK);
	}
	
	@PutMapping(path = "/update/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AddresseResponse> updateAddress(@PathVariable(name = "id") String addressId,@RequestBody AddresseRequest addresseRequest){
		ModelMapper modelMapper = new ModelMapper();
		AddresseDto addresseDto  = modelMapper.map(addresseRequest, AddresseDto.class);
		AddresseDto update = addressService.updateAddress(addressId, addresseDto);
		AddresseResponse addresseResponse = modelMapper.map(update, AddresseResponse.class);
		return new ResponseEntity<AddresseResponse>(addresseResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/delete/{addressId}")
	public ResponseEntity<String> deleteAddrese(@PathVariable String addressId) {
		addressService.deleteAddress(addressId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
