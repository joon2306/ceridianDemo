/**
 * 
 */
package com.ceridian.demo.democeridian.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Arjoon
 *
 */
@ControllerAdvice
public class DemoCeridianExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(DemoCeridianExceptionHandler.class);
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception e){
		logger.error(e.getMessage(), e);
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
