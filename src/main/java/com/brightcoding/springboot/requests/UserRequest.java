package com.brightcoding.springboot.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	
	@NotBlank(message = "Ce champ est obligatoire")
	private String firstName;
	
	@NotNull(message = "Le last name est obligatoire")
	@Size(min = 3, message = "Le first name doit etre superieure a 3 character")
	private String lastName;
	
	@NotNull
	@Email
	private String email;
	
	private Boolean role;
	
	@NotNull
	@Size(min = 8, message = "Size doit etre plus que 8")
	@Size(max = 12, message = "Size doit etre moins que 12")
	@Pattern(regexp="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
	message="ce mot de passe doit avoir des lettres en Maj et Minsc et numero")
	private String password;
	
	private List<AddresseRequest> addresses;
	
	private ContactRequest contact;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AddresseRequest> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddresseRequest> addresses) {
		this.addresses = addresses;
	}
	public ContactRequest getContact() {
		return contact;
	}
	public void setContact(ContactRequest contact) {
		this.contact = contact;
	}

}
