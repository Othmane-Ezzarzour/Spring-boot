package com.brightcoding.springboot.exceptions;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = -5026586388940777725L;

	public UserException(String message) {
		super(message);
	}


}
