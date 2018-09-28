package com.luv2code.springdemo.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Arvind Murthy To Handle global Exceptions
 *
 */
@ControllerAdvice
public class CustomerRestExceptionHandler {

	/*
	 * Add Exception handler for customer not found Exception
	 */
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {

		// create customer errorResponse Object.
		CustomerErrorResponse errorResponse = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),
				System.currentTimeMillis());

		// return entity

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	/*
	 * Add another Exception ... to catch any exception (catch all )
	 */
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {

		// create customer errorResponse Object.
		CustomerErrorResponse errorResponse = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(),
				exc.getMessage(), System.currentTimeMillis());

		// return entity

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

}
