package com.meli.mutant.service.impl;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.meli.mutant.dao.MutantDAO;
import com.meli.mutant.dto.DNASequenceDTO;
import com.meli.mutant.dto.StatsDTO;
import com.meli.mutant.model.Mutant;
import com.meli.mutant.service.MutantService;
import com.meli.mutant.util.MutantHelper;

@Service
public class MutantServiceImpl implements MutantService {

	@Autowired
	private MutantDAO mutantDAO;

	private static final String PATTERN_A = "AAAA";
	private static final String PATTERN_G = "GGGG";
	private static final String PATTERN_C = "CCCC";
	private static final String PATTERN_T = "TTTT";
	private static int ROW_SIZE;
	private static int COLUMN_SIZE;

	private static final int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static final int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };

	private void save(String dnaSequence, Boolean isMutant) {
		Mutant mutant = new Mutant();
		mutant.setDna(dnaSequence);
		mutant.setIsMutant(isMutant);
		mutantDAO.save(mutant);
	}

	@Async("taskExecutor")
	@Override
	public CompletableFuture<Boolean> isMutant(DNASequenceDTO dnaSequence) {
		System.out.println(Arrays.toString(dnaSequence.getDna()));
		MutantHelper.validateSize(dnaSequence.getDna());
		char[][] matrix = MutantHelper.validateContent(dnaSequence.getDna());
		setDimension(dnaSequence.getDna());
		AtomicInteger coincidences = new AtomicInteger(0);
		IntStream.range(0, ROW_SIZE).forEach(row -> {
			IntStream.range(0, COLUMN_SIZE).forEach(col -> {
				if (search(matrix, row, col, PATTERN_A) || search(matrix, row, col, PATTERN_G)
						|| search(matrix, row, col, PATTERN_C) || search(matrix, row, col, PATTERN_T)) {
					coincidences.addAndGet(1);
				}
			});
		});
		if (coincidences.get() >= 4) {
			save(Arrays.toString(dnaSequence.getDna()), true);
		} else {
			save(Arrays.toString(dnaSequence.getDna()), false);
		}
		return coincidences.get() >= 4 ? CompletableFuture.completedFuture(true)
				: CompletableFuture.completedFuture(false);
	}

	private void setDimension(String[] dna) {
		ROW_SIZE = dna[0].length();
		COLUMN_SIZE = dna.length;
	}

	private Boolean search(char[][] matrix, int row, int col, String dnaPattern) {
		if (matrix[row][col] != dnaPattern.charAt(0)) {
			return false;
		}
		int patternSize = dnaPattern.length();
		for (int dir = 0; dir < 8; dir++) {
			int index;
			int rowFlow = row + x[dir];
			int columnFlow = col + y[dir];
			// Primer caracter chequeado
			for (index = 1; index < patternSize; index++) {
				// Fuera de Rango
				if (rowFlow >= ROW_SIZE || rowFlow < 0 || columnFlow >= COLUMN_SIZE || columnFlow < 0) {
					break;
				}
				// Validamos coincidencia
				if (matrix[rowFlow][columnFlow] != dnaPattern.charAt(index)) {
					break;
				}
				// Salteamos posiciones
				rowFlow += x[dir];
				columnFlow += y[dir];
			}
			// Si todos los caracteres coinciden, es igual al patron buscado
			if (index == patternSize) {
				return true;
			}
		}
		return false;
	}

	@Override
	public StatsDTO getStats() {
		StatsDTO statsDTO = new StatsDTO();
		Long countHuman = mutantDAO.getCount(false);
		Long countMutant = mutantDAO.getCount(true);
		statsDTO.setCountMutantDNA(countMutant);
		statsDTO.setCountHumanDNA(countHuman);
		statsDTO.setRatio(calculateRatio(countHuman, countMutant));
		return statsDTO;
	}

	private float calculateRatio(Long countHuman, Long countMutant) {
		return (countHuman > 0) ? countHuman / countMutant : 0;
	}

}
