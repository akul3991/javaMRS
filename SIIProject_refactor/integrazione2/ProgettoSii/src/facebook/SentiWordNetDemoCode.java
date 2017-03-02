package facebook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import stemmer.SnowballStemmer;
import stemmer.italianStemmer;

public class SentiWordNetDemoCode {

	private Map<String, Double> dictionary;

	public SentiWordNetDemoCode(String pathToSWN) throws IOException {
		// This is our main dictionary representation
		dictionary = new HashMap<String, Double>();
		SnowballStemmer stemmer = (SnowballStemmer) new italianStemmer();
		BufferedReader csv = null;
		try {
			csv = new BufferedReader(new FileReader(pathToSWN));
			int lineNumber = 0;

			String line;
			while ((line = csv.readLine()) != null) {
				lineNumber++;

				// If it's a comment, skip this line.
				if (!line.trim().startsWith("#")) {
					// We use tab separation
					String[] data = line.split("\t");
					String wordTypeMarker = data[0];
					stemmer.setCurrent(wordTypeMarker);
                    stemmer.stem();
                    String stemmed = stemmer.getCurrent();
					Double wordValue=Double.parseDouble(data[5]);

					if (data.length != 7) {
						throw new IllegalArgumentException(
								"Incorrect tabulation format in file, line: "
										+ lineNumber);
					}
					this.dictionary.put(stemmed,wordValue);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (csv != null) {
				csv.close();
			}
		}
	}

	public double extract(String word) {
		if (dictionary.containsKey(word))
			return dictionary.get(word);
		else return 0;
	}
	
	public static void main(String [] args) throws IOException {
		/*if(args.length<1) {
			System.err.println("Usage: java SentiWordNetDemoCode <pathToSentiWordNetFile>");
			return;
		}*/
		String pathToSWN = "src/facebook/sentix.txt";
		SentiWordNetDemoCode sentiwordnet = new SentiWordNetDemoCode(pathToSWN);
		
		System.out.println("bello "+sentiwordnet.extract("bello"));
		System.out.println("divertente "+sentiwordnet.extract("divertente"));
		System.out.println("brutto"+sentiwordnet.extract("brutto"));
		System.out.println("triste "+sentiwordnet.extract("triste"));
	}
}
