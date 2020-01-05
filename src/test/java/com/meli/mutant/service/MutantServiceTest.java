package com.meli.mutant.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.meli.mutant.dao.MutantDAO;
import com.meli.mutant.dto.DNASequenceDTO;
import com.meli.mutant.dto.StatsDTO;
import com.meli.mutant.service.impl.MutantServiceImpl;

public class MutantServiceTest {

	@InjectMocks
	private MutantService mutantService = new MutantServiceImpl();

	@Mock
	private MutantDAO mockDao;

	private final String[] DNA_MUTANT = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
	private final String[] DNA_NOT_MUTANT = { "ATGCCA", "CTGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIsMutant() throws InterruptedException, ExecutionException {
		DNASequenceDTO dto = new DNASequenceDTO();
		dto.setDna(DNA_MUTANT);
		CompletableFuture<Boolean> isMutant = mutantService.isMutant(dto);
		assertTrue(isMutant.get());
	}

	@Test
	public void testIsNotMutant() throws InterruptedException, ExecutionException {
		DNASequenceDTO dto = new DNASequenceDTO();
		dto.setDna(DNA_NOT_MUTANT);
		CompletableFuture<Boolean> isMutant = mutantService.isMutant(dto);
		assertFalse(isMutant.get());
	}

	@Test
	public void testGetStat() {
		StatsDTO result = mutantService.getStats();
		assertNotNull(result);
	}

}
