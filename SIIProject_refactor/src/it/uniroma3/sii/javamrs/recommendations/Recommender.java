package it.uniroma3.sii.javamrs.recommendations;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.sii.javamrs.model.TrackEntity;
import it.uniroma3.sii.javamrs.model.UserEntity;
import it.uniroma3.sii.javamrs.model.functions.FunctionsLastFM;

public abstract class Recommender {
	
	private UserEntity target;
	
	public Recommender(UserEntity target) {
		this.target = target;
	}
	
	public abstract List<TrackEntity> recommend();
	
	protected boolean targetHasRecommendation() {
		return this.target.hasRecommendation();
	}
	
	protected List<TrackEntity> recommendNew() {
		List<TrackEntity> newRec = new ArrayList<>();
		
		List<TrackEntity> alreadyRec = this.target.getRecommendationEntity().getRecTracks();
		for (TrackEntity old : alreadyRec) {
			try {
				newRec.add(FunctionsLastFM.getSingleTrackSimilar(old.getArtist(), old.getTitle()));
			}
			catch (Exception e) {}
		}
		return newRec;
	}
	public UserEntity getTarget() {
		return this.target;
	}
}
