package com.sprint.cbm.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleException(NotFoundException ex){
		CustomErrorResponse response = new CustomErrorResponse();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMsg(ex.getMessage());
		response.setTimestamp(new Date());
		
		return new ResponseEntity<CustomErrorResponse>(response,HttpStatus.NOT_FOUND);
		
		
	}
	
	
	
	
	

}
