package it.uniroma3.sii.javamrs.utils.textprocessing.cleaner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parsing {
	
	public static Map<String, String> ruleParser(String inputFile, String separator) {
		Map<String, String> raw2norm = new HashMap<>();
		InputStream rules = Parsing.class.getResourceAsStream(inputFile);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(rules))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] keyValue = line.split(separator);
				String key = keyValue[0];
				String val = keyValue[1];
				raw2norm.put(key, val);
			}
			return raw2norm;
		}
		catch (IOException e) {
			e.printStackTrace();
			return raw2norm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return raw2norm;
		}
	}
}
