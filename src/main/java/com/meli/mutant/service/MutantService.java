package com.meli.mutant.service;

import java.util.concurrent.CompletableFuture;

import com.meli.mutant.dto.DNASequenceDTO;
import com.meli.mutant.dto.StatsDTO;

public interface MutantService {

    CompletableFuture<Boolean> isMutant(DNASequenceDTO dnaSequence);

    StatsDTO getStats();

}
