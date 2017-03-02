package it.uniroma3.sii.javamrs.utils.textprocessing.wordanalysis;

import it.uniroma3.sii.javamrs.utils.textprocessing.cleaner.TextCleaner;
import it.uniroma3.sii.javamrs.utils.textprocessing.stemmer.SnowballStemmer;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordCategorizer {
	
	private TextCleaner cleaner;
	private SnowballStemmer stemmer;
	private Map<String, String> wordMap;
	
	private Map<String, String> word2category = new TreeMap<>();
	
	public WordCategorizer(TextCleaner cleaner, SnowballStemmer stemmer, Map<String, String> wordMap) {
		this.cleaner = cleaner;
		this.stemmer = stemmer;
		this.wordMap = wordMap;
	}
	
	public void tagText(String text) {
		//Text preprocessing
		String clean = this.cleaner.cleanUp(text);
		String[] words = clean.split(" ");
		
		for (String word : words) {
			String stemmed = this.stem(word);
			this.tagWord(stemmed);
		}
	}
			
	public Map<String, Integer> getCategoryCounts() {
		Map<String, Integer> counts = new HashMap<>();
		for (String category : this.word2category.values()) {
			Integer prevCount = counts.get(category);
			if (prevCount == null)
				counts.put(category, 1);
			else
				counts.put(category, prevCount + 1);
		}
		return counts;
	}
	
	private String stem(String word) {
		this.stemmer.setCurrent(word);
		this.stemmer.stem();
		return this.stemmer.getCurrent();
	}
	
	private void tagWord(String word) {
		String category = this.wordMap.get(word);
		if (category != null) {
			this.word2category.put(word, category);
		}
	}
}
