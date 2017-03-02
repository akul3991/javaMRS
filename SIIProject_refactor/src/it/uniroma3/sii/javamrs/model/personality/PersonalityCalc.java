package it.uniroma3.sii.javamrs.model.personality;

import java.util.Locale;

import it.uniroma3.sii.javamrs.model.Personality;

public class PersonalityCalc {
	
	//Indici dei valori rilevanti per ciascuno dei tratti di personalità
	private final Integer[] EXT_INDICES = {0, 5, 10, 15, 20, 25, 30, 35};
	private final Integer[] OPE_INDICES = {4, 9, 14, 19, 24, 29, 34, 39, 40, 43};
	private final Integer[] AGR_INDICES = {1, 6, 11, 16, 21, 26, 31, 36, 41};
	private final Integer[] CON_INDICES = {2, 7, 12, 17, 22, 27, 32, 37, 42};
	private final Integer[] NEU_INDICES = {3, 8, 13, 18, 23, 28, 33, 38};
	
	//Elenco dei valori che devono essere complementati nel calcolo della personalità
	private final Integer[] VALUES_TO_REVERSE = {1, 5, 7, 8, 11, 17, 20, 22, 23, 26, 30, 33, 34, 36, 40, 42};
	
	private Integer[] testValues;
	
	public PersonalityCalc(Integer[] testValues) {
		this.testValues = testValues;
	}
		
	public Personality calculate() {
		//Complementa i valori specificati
		reverseScores(VALUES_TO_REVERSE);
		
		//Calcola il valore di ogni tratto di personalità
		Double extValue = truncate(calculateMean(EXT_INDICES), 3);
		Double opeValue = truncate(calculateMean(OPE_INDICES), 3);
		Double agrValue = truncate(calculateMean(AGR_INDICES), 3);
		Double conValue = truncate(calculateMean(CON_INDICES), 3);
		Double neuValue = truncate(calculateMean(NEU_INDICES), 3);
		
		return new Personality(extValue, opeValue, agrValue, conValue, neuValue);
	}
	
	private Double truncate(Double value, int decimals) {
		String floatFormat = "%." + decimals + "f";
		return Double.valueOf(String.format(Locale.US, floatFormat, value));
	}
	
	private Double calculateMean(Integer[] valuesIndices) {
		double sum = 0;
		for (Integer index : valuesIndices) {
			sum += this.testValues[index];
		}
		return sum/valuesIndices.length;
	}
	
	private void reverseScores(Integer[] reverseIndices) {
		for (Integer toReverse : reverseIndices) {
			this.testValues[toReverse] = 6 - this.testValues[toReverse];
		}
	}
}
