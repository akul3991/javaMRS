package it.uniroma3.sii.javamrs.recommendations;

import it.uniroma3.sii.javamrs.model.TrackEntity;
import it.uniroma3.sii.javamrs.model.UserEntity;
import it.uniroma3.sii.javamrs.model.functions.FunctionsByGenre;
import it.uniroma3.sii.javamrs.model.functions.FunctionsLastFM;
import it.uniroma3.sii.javamrs.recommendations.utils.GenreRules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonalityGenresRecommender extends Recommender {
	
	public PersonalityGenresRecommender(UserEntity target) {
		super(target);
	}

	public List<TrackEntity> recommend() {
		List<TrackEntity> recTracks = new ArrayList<>();
		//Calcola una nuova raccomandazione se non esiste per il target
		if (!this.targetHasRecommendation()) {
			List<String> recGenres = GenreRules.getGenre(this.getTarget());
			try {
				for (String genre : recGenres)
					recTracks.addAll(FunctionsLastFM.getTrackBygenres(genre));
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
}
