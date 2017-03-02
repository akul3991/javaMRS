package it.uniroma3.sii.javamrs.recommendations;

import it.uniroma3.sii.javamrs.model.TrackEntity;
import it.uniroma3.sii.javamrs.model.UserEntity;
import it.uniroma3.sii.javamrs.model.functions.FunctionsByGenre;
import it.uniroma3.sii.javamrs.model.functions.FunctionsLastFM;
import it.uniroma3.sii.javamrs.model.functions.FunctionsRec;
import it.uniroma3.sii.javamrs.recommendations.utils.GenreRules;
import it.uniroma3.sii.javamrs.recommendations.utils.NearestNeighbor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreferenceRecommender extends Recommender {
	
	private List<TrackEntity> favoriteTracks;
	public PreferenceRecommender(UserEntity target, List<TrackEntity> favorite) {
		super(target);
		this.favoriteTracks = favorite;
	}

	public List<TrackEntity> recommend() {
		List<TrackEntity> recTracks = new ArrayList<>();
		//Calcola una nuova raccomandazione se non esiste per il target
		if (!this.targetHasRecommendation()) {
			try {
				recTracks = FunctionsRec.findRecommendationsPreference(this.favoriteTracks);
			}
			catch (IOException e) {
				e.printStackTrace();
				System.out.println("Connessione fallita");
			}
		}
		else {
			recTracks = this.recommendNew();
		}
		return recTracks;
	}
	
	public List<TrackEntity> getFavoriteTracks() {
		return this.favoriteTracks;
	}
}
