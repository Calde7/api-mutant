package com.meli.mutant.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SizeException.class)
	public final ResponseEntity<GenericException> sizeException(SizeException ex) {
		GenericException exception = new GenericException(LocalDateTime.now(), ex.getMessage(),
				HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<GenericException>(exception, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ContentException.class)
	public final ResponseEntity<GenericException> contentException(ContentException ex) {
		GenericException exception = new GenericException(LocalDateTime.now(), ex.getMessage(),
				HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<GenericException>(exception, HttpStatus.BAD_REQUEST);
	}

}
