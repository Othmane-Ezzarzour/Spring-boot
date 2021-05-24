package com.brightcoding.springboot.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
	
	private static final long serialVersionUID = -8064754421184761389L;
	
	private int id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean role;
	private String password;
	private String encryptePassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus;
	private List<AddresseDto> addresses; 
	private ContactDto contact;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean isRole() {
		return role;
	}
	public void setRole(Boolean role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptePassword() {
		return encryptePassword;
	}
	public void setEncryptePassword(String encryptePassword) {
		this.encryptePassword = encryptePassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public List<AddresseDto> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddresseDto> addresses) {
		this.addresses = addresses;
	}
	public ContactDto getContact() {
		return contact;
	}
	public void setContact(ContactDto contact) {
		this.contact = contact;
	}
	
}
