package com.meli.mutant.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseExceptionHandlerTest {

	@Test
	public void testSizeExceptionHandler() {
		String expMessage = "Los elementos del arreglo deben tener el mismo tamaño";
		ResponseExceptionHandler instance = new ResponseExceptionHandler();
		ResponseEntity<GenericException> result = instance.sizeException(new SizeException(expMessage));
		assertEquals(expMessage, result.getBody().getMessage());
		assertEquals(HttpStatus.BAD_REQUEST.name(), result.getBody().getDetail());
	}

	@Test
	public void testContentExceptionHandler() {
		String expMessage = "Los elementos que componen a la matriz son A, T, C o G";
		ResponseExceptionHandler instance = new ResponseExceptionHandler();
		ResponseEntity<GenericException> result = instance.contentException(new ContentException(expMessage));
		assertEquals(expMessage, result.getBody().getMessage());
		assertEquals(HttpStatus.BAD_REQUEST.name(), result.getBody().getDetail());
	}

}
