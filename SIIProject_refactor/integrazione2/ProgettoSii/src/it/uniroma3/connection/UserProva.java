package it.uniroma3.connection;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import LastFM.functions_lastFM;
import Objects.*;
import recommendations.functions_by_genre;
import recommendations.personality_genres;

public class UserProva {

	public static void main(String[] args) throws IOException, JSONException {
		DBfunctions fun= new DBfunctions();
		functions_by_genre f=new functions_by_genre();
		List<String> lista=f.getGenre("mike92");
		for(String canzone: lista){
			System.out.println(canzone);
		}
		System.out.println("finito");
		
		
	}
}