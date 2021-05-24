package com.brightcoding.springboot.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.brightcoding.springboot.responses.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value= UserException.class)
	public ResponseEntity<Object> handleUserException(UserException userException, WebRequest webRequest) {
		
		ErrorMessage errotMessage = new ErrorMessage(new Date(), userException.getMessage());
		
		return new ResponseEntity<Object>(errotMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	   
	@ExceptionHandler(value= Exception.class)
	public ResponseEntity<Object> handleAllException(Exception exception, WebRequest webRequest) {
		
		ErrorMessage errotMessage = new ErrorMessage(new Date(), exception.getMessage());
		
		return new ResponseEntity<Object>(errotMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(value= MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest webRequest) {
		
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		return new ResponseEntity<Object>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		
	}
}
