package com.meli.mutant.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.meli.mutant.dto.DNASequenceDTO;
import com.meli.mutant.dto.StatsDTO;
import com.meli.mutant.service.MutantService;

public class MutantControllerTest {

    @Mock
    private MutantService mockService;

    private MutantController mockController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockController = new MutantController(mockService);
    }

    @Test
    public void testIsMutantTrue() throws Exception {
        String[] dnaList = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        DNASequenceDTO dto = new DNASequenceDTO();
        dto.setDna(dnaList);
        when(mockService.isMutant(dto)).thenReturn(CompletableFuture.completedFuture(true));
        ResponseEntity<String> responseEntity = mockController.save(dto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testIsMutantFalse() throws Exception {
        String[] dnaList = { "TTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        DNASequenceDTO dto = new DNASequenceDTO();
        dto.setDna(dnaList);
        when(mockService.isMutant(dto)).thenReturn(CompletableFuture.completedFuture(false));
        ResponseEntity<String> responseEntity = mockController.save(dto);
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testGetStats() throws Exception {
        StatsDTO dto = new StatsDTO();
        dto.setCountHumanDNA(10L);
        dto.setCountMutantDNA(4L);
        dto.setRatio(dto.getCountHumanDNA() / dto.getCountMutantDNA());
        when(mockService.getStats()).thenReturn(dto);
        ResponseEntity<StatsDTO> responseEntity = mockController.getStats();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
