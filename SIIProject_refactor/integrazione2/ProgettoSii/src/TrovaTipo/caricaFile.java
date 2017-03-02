package TrovaTipo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cleaner.Parsing;
import cleaner.TextCleaner;
import stemmer.SnowballStemmer;
import stemmer.italianStemmer;

public class caricaFile {
	
	private Map<String, String> tipoParola;
	
	public caricaFile() throws IOException{
		Parsing parse=new Parsing();
		Map<String, String> rule= parse.ruleParser("src/cleaner/normRules.txt", "=");
	    TextCleaner clean=new TextCleaner(rule);
		SnowballStemmer stemmer = (SnowballStemmer) new italianStemmer();
		tipoParola = new HashMap<String,String>();
		BufferedReader csv = null;
		try {
			csv = new BufferedReader(new FileReader("src/TrovaTipo/Parolacce.txt"));
			String line;
			while ((line = csv.readLine()) != null) {
				line=clean.cleanUp(line);
				stemmer.setCurrent(line);
                stemmer.stem();
                line = stemmer.getCurrent();
				tipoParola.put(line,"swear");
			}
				csv = new BufferedReader(new FileReader("src/TrovaTipo/Parolepositive.txt"));
				while ((line = csv.readLine()) != null) {
					line=clean.cleanUp(line);
					stemmer.setCurrent(line);
	                stemmer.stem();
	                line = stemmer.getCurrent();
					tipoParola.put(line,"positive");
				}
				csv = new BufferedReader(new FileReader("src/TrovaTipo/Cibi.txt"));
				while ((line = csv.readLine()) != null) {
					line=clean.cleanUp(line);
					stemmer.setCurrent(line);
	                stemmer.stem();
	                line = stemmer.getCurrent();
					tipoParola.put(line,"food");
				}
				csv = new BufferedReader(new FileReader("src/TrovaTipo/Parolenegative.txt"));
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
