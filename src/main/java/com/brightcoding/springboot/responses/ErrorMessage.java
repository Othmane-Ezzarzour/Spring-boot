package com.brightcoding.springboot.responses;

import java.util.Date;

public class ErrorMessage {

	private Date timestemp;
	private String message;
	
	public ErrorMessage(Date timestemp, String message) {
		super();
		this.timestemp = timestemp;
		this.message = message;
	}

	public Date getTimestemp() {
		return timestemp;
	}

	public void setTimestemp(Date timestemp) {
		this.timestemp = timestemp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
