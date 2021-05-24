package com.brightcoding.springboot.responses;

import java.util.List;

public class UserResponse {

	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean role;
	private List<AddresseResponse> addresses;
	private ContactResponse contact;

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

	public Boolean getRole() {
		return role;
	}

	public void setRole(Boolean role) {
		this.role = role;
	}

	public List<AddresseResponse> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddresseResponse> addresses) {
		this.addresses = addresses;
	}

	public ContactResponse getContact() {
		return contact;
	}

	public void setContact(ContactResponse contact) {
		this.contact = contact;
	}

}
