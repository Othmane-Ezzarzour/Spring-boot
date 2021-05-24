package com.brightcoding.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brightcoding.springboot.modeles.Addresses;
import com.brightcoding.springboot.modeles.Users;

@Repository
public interface AddressRepository extends JpaRepository<Addresses, Long> {
	
	public List<Addresses> findByUser(Users currentUser);
	
	public Addresses findByAddressId(String addressId);
	
	public Addresses findById(long id);

}
