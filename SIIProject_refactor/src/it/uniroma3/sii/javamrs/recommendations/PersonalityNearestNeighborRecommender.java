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

public class PersonalityNearestNeighborRecommender extends Recommender {
	
	private final int DEFAULT_NEIGHBORHOOD_SIZE = 5;
	private final int DEFAULT_TRACKS_PER_NEIGHBOR = 3;
	
	private List<UserEntity> userSet;
	private int neighSize;
	private int tracksPerNeigh;
	
	public PersonalityNearestNeighborRecommender(UserEntity target, List<UserEntity> userSet) {
		super(target);
		this.userSet = userSet;
		this.neighSize = DEFAULT_NEIGHBORHOOD_SIZE;
		this.tracksPerNeigh = DEFAULT_TRACKS_PER_NEIGHBOR;
	}

	public List<TrackEntity> recommend() {
		List<TrackEntity> recTracks = new ArrayList<>();
		//Calcola una nuova raccomandazione se non esiste per il target
		if (!this.targetHasRecommendation()) {
			List<UserEntity> neighbors = NearestNeighbor.findNeighborhood(this.getTarget(), this.getUserSet(), this.getNeighSize());
			
			for (UserEntity neighbor : neighbors) {
				if (neighbor.hasRecommendation()) {
					List<TrackEntity> shuffledRecs = new ArrayList<>(neighbor.getRecommendationEntity().getRecTracks());
					Collections.shuffle(shuffledRecs);
					List<TrackEntity> filtered = shuffledRecs.subList(0, Math.min(3, shuffledRecs.size()));
					
					for(TrackEntity te : filtered) {
						recTracks.add(new TrackEntity(te.getTitle(), te.getArtist()));
					}
				}
			}
		}
		else {
			recTracks = this.recommendNew();
		}
		return recTracks;
	}
	
	public List<UserEntity> getUserSet() {
		return this.userSet;
	}
	
	public int getNeighSize() {
		return this.neighSize;
	}
	
	public int getTracksPerNeigh() {
		return this.tracksPerNeigh;
	}
}
