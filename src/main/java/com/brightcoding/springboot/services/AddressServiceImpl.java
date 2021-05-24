package com.brightcoding.springboot.services;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brightcoding.springboot.modeles.Addresses;
import com.brightcoding.springboot.modeles.Users;
import com.brightcoding.springboot.repository.AddressRepository;
import com.brightcoding.springboot.repository.UserRepository;
import com.brightcoding.springboot.shared.Utils;
import com.brightcoding.springboot.shared.dto.AddresseDto;
import com.brightcoding.springboot.shared.dto.UserDto;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Utils util;
	
	@Override
	public List<AddresseDto> getAddresses(String email) {
		Users curentUser = userRepository.findByEmail(email);
		
		List<Addresses> addresses = curentUser.getRole() == true ? (List<Addresses>) addressRepository.findAll() : addressRepository.findByUser(curentUser);
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<AddresseDto>>() {}.getType();
		List<AddresseDto> addresseDto = modelMapper.map(addresses, listType);
		return addresseDto;
	}

	@Override
	public AddresseDto createAddresse(AddresseDto addresseDto, String email) {
		Users currentUser = userRepository.findByEmail(email);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto save = modelMapper.map(currentUser, UserDto.class);
		
		addresseDto.setAddressId(util.generateStringId(30));
		addresseDto.setUser(save);
		
		Addresses Address = modelMapper.map(addresseDto, Addresses.class); 
		
		Addresses saveAddrese = addressRepository.save(Address);
		AddresseDto addresseDtoSave = modelMapper.map(saveAddrese, AddresseDto.class);
		return addresseDtoSave;
	}
	
	public AddresseDto getOneAddress(String addressId) {
		Addresses addresse = addressRepository.findByAddressId(addressId);
		ModelMapper modelMapper = new ModelMapper();
		AddresseDto addresseDto = modelMapper.map(addresse, AddresseDto.class);
		return addresseDto;
	}
	
	@Override
	public AddresseDto updateAddress(String addressId, AddresseDto updateAddress) {
		Addresses address = addressRepository.findByAddressId(addressId);
		if(address == null) {
			throw new UsernameNotFoundException(addressId);
		}
		address.setCity(updateAddress.getCity());
		address.setCountry(updateAddress.getCountry());
		address.setStreet(updateAddress.getStreet());
		address.setCodePostal(updateAddress.getCodePostal());
		address.setType(updateAddress.getType());
		
		Addresses addressSave = addressRepository.save(address);
		
		ModelMapper mM = new ModelMapper();
		AddresseDto addresseDto = mM.map(addressSave, AddresseDto.class);
		
		return addresseDto;
	}
	
	@Override
	public void deleteAddress(String addressId) {
		Addresses address = addressRepository.findByAddressId(addressId);
		if(address == null) {
			throw new UsernameNotFoundException(addressId);
		}
		addressRepository.delete(address);
	}

}
