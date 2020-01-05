package com.meli.mutant.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.meli.mutant.exception.ContentException;
import com.meli.mutant.exception.SizeException;

public class MutantHelperTest {

	private final String[] DNA_MUTANT = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
	private final String[] DNA_ERROR_SIZE = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA" };
	private final String[] DNA_ERROR_CONTENT = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "PEPEPE" };

	@Test(expected = SizeException.class)
	public void testValidateSize() {
		MutantHelper.validateSize(DNA_ERROR_SIZE);
	}

	@Test
	public void testValidateContent() {
		char[][] matrix = MutantHelper.validateContent(DNA_MUTANT);
		assertNotNull(matrix);
	}

	@Test(expected = ContentException.class)
	public void testValidateContentException() {
		MutantHelper.validateContent(DNA_ERROR_CONTENT);
	}

}
