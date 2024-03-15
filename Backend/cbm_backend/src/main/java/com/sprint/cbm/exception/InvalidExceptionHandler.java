package com.sprint.cbm.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidExceptionHandler {
	
	@ExceptionHandler(InvalidException.class)
	public ResponseEntity<CustomInvalidResponse> handleException(InvalidException ex){
		CustomInvalidResponse  response = new CustomInvalidResponse();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMsg(ex.getMessage());
		response.setTimestamp(new Date());
		
		return new ResponseEntity<CustomInvalidResponse>(response,HttpStatus.BAD_REQUEST);
		
		
	}

}
