package com.meli.mutant.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.meli.mutant.model.Mutant;

public class MutantDAOTest {

    private final String[] DNA_MUTANT = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

    @Mock
    MutantDAO dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() throws Exception {
        Mutant mutant = new Mutant();
        mutant.setDna(Arrays.toString(DNA_MUTANT));
        mutant.setIsMutant(true);
        when(dao.save(mutant)).thenReturn(mutant);
        Mutant expected = dao.save(mutant);
        assertEquals(expected, mutant);
    }

}
