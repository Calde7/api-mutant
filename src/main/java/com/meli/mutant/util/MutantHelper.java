package com.meli.mutant.util;

import com.meli.mutant.exception.ContentException;
import com.meli.mutant.exception.SizeException;

public class MutantHelper {

	public static int ROW_SIZE;
	public static int COLUMN_SIZE;

	private static void setDimension(String[] dna) {
		ROW_SIZE = dna[0].length();
		COLUMN_SIZE = dna.length;
	}

	public static void validateSize(String[] dna) {
		setDimension(dna);
		for (int i = 0; i < ROW_SIZE; i++) {
			if (COLUMN_SIZE != dna[i].length()) {
				throw new SizeException("Los elementos del arreglo deben tener el mismo tamaño");
			}
		}
	}

	public static char[][] validateContent(String[] dna) throws IllegalArgumentException {
		setDimension(dna);
		char[][] matrixDna = new char[ROW_SIZE][COLUMN_SIZE];
		for (int i = 0; i < ROW_SIZE; i++) {
			char[] row = dna[i].toCharArray();
			for (int j = 0; j < row.length; j++) {
				if (row[j] == 'A' || row[j] == 'G' || row[j] == 'C' || row[j] == 'T') {
					matrixDna[i][j] = row[j];
				} else {
					throw new ContentException("Los elementos que componen a la matriz son A, T, C o G");
				}
			}
		}
		return matrixDna;
	}

}
