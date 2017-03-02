package it.uniroma3.sii.javamrs.utils.textprocessing.wordanalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.sii.javamrs.utils.textprocessing.cleaner.Parsing;
import it.uniroma3.sii.javamrs.utils.textprocessing.cleaner.TextCleaner;
import it.uniroma3.sii.javamrs.utils.textprocessing.stemmer.SnowballStemmer;
import it.uniroma3.sii.javamrs.utils.textprocessing.stemmer.italianStemmer;

public class FileLoader {
	
	private final String NORMRULES_FILE = "normRules.txt";
	private final String NORMRULES_SEPARATOR = "=";
	
	private final String SWEARWORDS_FILE = "Parolacce.txt";
	private final String POSITIVEWORDS_FILE = "Parolepositive.txt";
	private final String FOODWORDS_FILE = "Cibi.txt";
	private final String NEGATIVEWORDS_FILE = "Parolenegative.txt";
	
	private Map<String, String> tipoParola;
	
	private TextCleaner cleaner;
	private SnowballStemmer stemmer;
	
	public FileLoader(TextCleaner cleaner, SnowballStemmer stemmer) {
		this.cleaner = cleaner;
		this.stemmer = stemmer;
	}
	
	public Map<String, String> loadParole() throws IOException {
		Map<String, String> tipoParola = new HashMap<String, String>();
		BufferedReader csv = null;
		try {
			csv = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(SWEARWORDS_FILE)));
			String line;
			while ((line = csv.readLine()) != null) {
				line=cleaner.cleanUp(line);
				stemmer.setCurrent(line);
                stemmer.stem();
                line = stemmer.getCurrent();
				tipoParola.put(line,"swear");
			}
				csv = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(POSITIVEWORDS_FILE)));
				while ((line = csv.readLine()) != null) {
					line=cleaner.cleanUp(line);
					stemmer.setCurrent(line);
	                stemmer.stem();
	                line = stemmer.getCurrent();
					tipoParola.put(line,"positive");
				}
				csv = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(FOODWORDS_FILE)));
				while ((line = csv.readLine()) != null) {
					line=cleaner.cleanUp(line);
					stemmer.setCurrent(line);
	                stemmer.stem();
	                line = stemmer.getCurrent();
					tipoParola.put(line,"food");
				}
				csv = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(NEGATIVEWORDS_FILE)));
				while ((line = csv.readLine()) != null) {
					line=cleaner.cleanUp(line);
					stemmer.setCurrent(line);
	                stemmer.stem();
	                line = stemmer.getCurrent();
					tipoParola.put(line,"negative");
				}
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipoParola;
	}
	
	public FileLoader() throws IOException{
		Map<String, String> rule= Parsing.ruleParser(NORMRULES_FILE, NORMRULES_SEPARATOR);
	    TextCleaner clean=new TextCleaner(rule);
		SnowballStemmer stemmer = (SnowballStemmer) new italianStemmer();
		tipoParola = new HashMap<String,String>();
		BufferedReader csv = null;
		try {
			csv = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(SWEARWORDS_FILE)));
			String line;
			while ((line = csv.readLine()) != null) {
				line=clean.cleanUp(line);
				stemmer.setCurrent(line);
                stemmer.stem();
                line = stemmer.getCurrent();
				tipoParola.put(line,"swear");
			}
				csv = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(POSITIVEWORDS_FILE)));
				while ((line = csv.readLine()) != null) {
					line=clean.cleanUp(line);
					stemmer.setCurrent(line);
	                stemmer.stem();
	                line = stemmer.getCurrent();
					tipoParola.put(line,"positive");
				}
				csv = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(FOODWORDS_FILE)));
				while ((line = csv.readLine()) != null) {
					line=clean.cleanUp(line);
					stemmer.setCurrent(line);
	                stemmer.stem();
	                line = stemmer.getCurrent();
					tipoParola.put(line,"food");
				}
				csv = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(NEGATIVEWORDS_FILE)));
				while ((line = csv.readLine()) != null) {
					line=clean.cleanUp(line);
					stemmer.setCurrent(line);
	                stemmer.stem();
	                line = stemmer.getCurrent();
					tipoParola.put(line,"negative");
				}
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String,String> getTipi(){
		return tipoParola;
	}
}
