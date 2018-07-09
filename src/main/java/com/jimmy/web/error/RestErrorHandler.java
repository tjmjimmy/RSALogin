package com.jimmy.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jimmy.web.exception.BusinessException;

@ControllerAdvice(annotations=RestController.class)
public class RestErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<BusinessException> processBusinessException(BusinessException ex){
		
		return new ResponseEntity<BusinessException>(ex, HttpStatus.CONFLICT);
	}
}
