package com.brightcoding.springboot.services;

import java.util.List;

import com.brightcoding.springboot.shared.dto.AddresseDto;

public interface AddressService {

	public List<AddresseDto> getAddresses(String email);
	
	public AddresseDto createAddresse(AddresseDto save, String email);
	
	public AddresseDto getOneAddress(String addressId);
	
	public AddresseDto updateAddress(String addressId, AddresseDto updateAddress);
	
	public void deleteAddress(String addressId);

}
