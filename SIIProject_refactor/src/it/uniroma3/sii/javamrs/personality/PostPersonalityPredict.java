package it.uniroma3.sii.javamrs.personality;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import facebook4j.Post;
import it.uniroma3.sii.javamrs.model.Big5Entity;
import it.uniroma3.sii.javamrs.utils.textprocessing.cleaner.TextCleaner;
import it.uniroma3.sii.javamrs.utils.textprocessing.stemmer.SnowballStemmer;
import it.uniroma3.sii.javamrs.utils.textprocessing.wordanalysis.FileLoader;
import it.uniroma3.sii.javamrs.utils.textprocessing.wordanalysis.WordCategorizer;

public class PostPersonalityPredict {
	
	private TextCleaner cleaner;
	private SnowballStemmer stemmer;
	
	private Map<String, String> wordMap;
	private Map<String, Double[]> categoryCoeff = new HashMap<>();
	
	private void initCoeffMap() {
		this.categoryCoeff.put("swear", new Double[] {0.006, -0.171, 0.032, -0.084, -0.120});
		this.categoryCoeff.put("food", new Double[] {-0.098, -0.050, 0.029, 0.031, 0.207});
		this.categoryCoeff.put("negative", new Double[] {0.044, -0.150, 0.008, 0.101, 0.192});
		this.categoryCoeff.put("positive", new Double[] {0.052, 0.045, 0.117, 0.167, -0.013});
	}
	public PostPersonalityPredict(TextCleaner cleaner, SnowballStemmer stemmer) {
		this.cleaner = cleaner;
		this.stemmer = stemmer;
		try {
			this.wordMap = new FileLoader(this.cleaner, this.stemmer).loadParole();
		}
		catch (IOException e) {
			e.printStackTrace();
			this.wordMap = new TreeMap<>();
		}
		this.initCoeffMap();
	}
	
	public Big5Entity makePrediction(List<Post> postList) {
		Double[] total = {0.0, 0.0, 0.0, 0.0, 0.0};
		for (Post p : postList) {
			Double[] partial = this.singlePrediction(p);
			total[0] += partial[0];
			total[1] += partial[1];
			total[2] += partial[2];
			total[3] += partial[3];
			total[4] += partial[4];
		}
		return new Big5Entity(total[0], total[1], total[2], total[3], total[4]);
	}
	
	public Big5Entity makePrediction(Post post) {
		Double[] res = this.singlePrediction(post);
		return new Big5Entity(res[0], res[1], res[2], res[3], res[4]);
	}
	
	private Double[] singlePrediction(Post post) {
		String text = post.getMessage();
		if (text == null)
			return new Double[] {0.0, 0.0, 0.0, 0.0, 0.0};
		
		WordCategorizer categorizer = new WordCategorizer(this.cleaner, this.stemmer, this.wordMap);
		categorizer.tagText(text);
		Map<String, Integer> categoryCounts = categorizer.getCategoryCounts();
		return this.calculateBig5FromCounts(categoryCounts);
	}
	
//	public Big5Entity makePrediction(Post post) {
//		String text = post.getMessage();
//		
//		WordCategorizer categorizer = new WordCategorizer(this.cleaner, this.stemmer, this.wordMap);
//		categorizer.tagText(text);
//		Map<String, Integer> categoryCounts = categorizer.getCategoryCounts();
//		return this.calculateBig5FromCounts(categoryCounts);
//	}
	
	private Double[] calculateBig5FromCounts(Map<String, Integer> categoryCounts) {
		Double ope = 0.0;
		Double con = 0.0;
		Double ext = 0.0;
		Double agr = 0.0;
		Double neu = 0.0;
		for (String category : categoryCounts.keySet()) {
			Double[] coefficients = this.categoryCoeff.get(category);
			Integer numInCategory = categoryCounts.get(category);
			ope += coefficients[0] * numInCategory;
			con += coefficients[1] * numInCategory;
			ext += coefficients[2] * numInCategory;
			agr += coefficients[3] * numInCategory;
			neu += coefficients[4] * numInCategory;
		}
		return new Double[] {ope, agr, con, neu, ext};
	}
}
