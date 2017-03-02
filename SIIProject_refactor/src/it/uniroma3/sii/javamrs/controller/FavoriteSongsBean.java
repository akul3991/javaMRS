package it.uniroma3.sii.javamrs.controller;

import it.uniroma3.sii.javamrs.controller.facade.UserFacade;
import it.uniroma3.sii.javamrs.model.TrackEntity;
import it.uniroma3.sii.javamrs.model.functions.FunctionsLastFM;
import it.uniroma3.sii.javamrs.model.functions.FunctionsOther;
import it.uniroma3.sii.javamrs.model.functions.FunctionsRec;
import it.uniroma3.sii.javamrs.recommendations.PreferenceRecommender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(eager=true)
@SessionScoped
public class FavoriteSongsBean {
	
	private List<TrackEntity> suggested;
	
	//Parametri di richiesta
	private String newTrack;
	private String chosenTrack;
	private String trackToRemove;
	private String[] favoriteTracks;
	
	private List<TrackEntity> favoriteEntities = new ArrayList<>();
	private Map<String, TrackEntity> favored = new HashMap<>();
	
	private String errore;
	private String url;
	
	//Bean di sessione
	@ManagedProperty(value="#{userSessionBean}")
	private UserSessionBean sessionBean;
	
	//User facade
	@EJB(beanName="userFacade")
	private UserFacade facade;
	
	public FavoriteSongsBean() {}
	
	@PostConstruct
	public void init() {
		try {
			this.suggested = FunctionsOther.getSuggestions();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Connessione fallita");
			this.suggested = Arrays.asList(new TrackEntity("ERRORE", e.getMessage()));
		}
	}
	
	//Actions
	public void giveSimilarTracks() {
		String[] strArray = this.chosenTrack.split(":");
		String trackArtist = strArray[0];
		String trackTitle = strArray[1];
		this.favored.put(this.chosenTrack, new TrackEntity(trackTitle, trackArtist));
		
		try {
			List<TrackEntity> newSuggestions = FunctionsLastFM.getTrackSimilar(trackArtist, trackTitle);
			if (!newSuggestions.isEmpty()) {
				this.suggested.clear();
				this.suggested.addAll(newSuggestions);
			}
			this.chosenTrack = null;
		}
		catch (IOException e) {
			this.errore = "Errore";
			e.printStackTrace();
			System.out.println("Connessione fallita");
		}
	}
		
	public String searchNewTrack() {
		try {
			List<TrackEntity> newSuggestions = FunctionsLastFM.searchTrack(this.newTrack);
			this.suggested.clear();
			this.suggested.addAll(newSuggestions);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Connessione fallita");
		}
		return null;
	}
	
	public String removeTrack() {
		this.favored.remove(this.trackToRemove);
		return null;
	}
	
	public String recommend() {
		//Cancella la precedente raccomandazione se presente
		if (this.sessionBean.getCurrentUser().hasRecommendation())
			this.facade.removeRecommendation(this.sessionBean.getCurrentUser());
		
		List<TrackEntity> favorite = new ArrayList<>(this.favored.values());
		List<TrackEntity> recommended = new PreferenceRecommender(this.sessionBean.getCurrentUser(), favorite)
		                                    .recommend();
		
		this.facade.addRecommendation(this.sessionBean.getCurrentUser(), recommended);
		return "recommendedTracksByPref";
	}
	//Getters & Setters
	public String getNewTrack() {
		return newTrack;
	}

	public void setNewTrack(String newTrack) {
		this.newTrack = newTrack;
	}

	public List<TrackEntity> getSuggested() {
		return suggested;
	}

	public void setSuggested(List<TrackEntity> suggested) {
		this.suggested = suggested;
	}

	public UserSessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(UserSessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public String[] getFavoriteTracks() {
		return favoriteTracks;
	}

	public void setFavoriteTracks(String[] favoriteTracks) {
		this.favoriteTracks = favoriteTracks;
	}

	public List<TrackEntity> getFavoriteEntities() {
		return favoriteEntities;
	}

	public void setFavoriteEntities(List<TrackEntity> favoriteEntities) {
		this.favoriteEntities = favoriteEntities;
	}

	public String getErrore() {
		return errore;
	}

	public void setErrore(String errore) {
		this.errore = errore;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getChosenTrack() {
		return chosenTrack;
	}

	public void setChosenTrack(String chosenTrack) {
		this.chosenTrack = chosenTrack;
	}

	public String getTrackToRemove() {
		return trackToRemove;
	}

	public void setTrackToRemove(String trackToRemove) {
		this.trackToRemove = trackToRemove;
	}

	public Map<String, TrackEntity> getFavored() {
		return favored;
	}

	public void setFavored(Map<String, TrackEntity> favored) {
		this.favored = favored;
	}

	public UserFacade getUserFacade() {
		return facade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.facade = userFacade;
	}
}
