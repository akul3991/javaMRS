package it.uniroma3.sii.javamrs.controller;

import facebook4j.Facebook;
import facebook4j.Friend;
import facebook4j.Post;
import facebook4j.Reading;
import it.uniroma3.sii.javamrs.controller.facade.UserFacade;
import it.uniroma3.sii.javamrs.model.Big5Entity;
import it.uniroma3.sii.javamrs.model.RecommendationEntity;
import it.uniroma3.sii.javamrs.model.TrackEntity;
import it.uniroma3.sii.javamrs.model.UserEntity;
import it.uniroma3.sii.javamrs.model.functions.FunctionsByGenre;
import it.uniroma3.sii.javamrs.model.functions.FunctionsLastFM;
import it.uniroma3.sii.javamrs.model.functions.FunctionsRec;
import it.uniroma3.sii.javamrs.personality.PostPersonalityPredict;
import it.uniroma3.sii.javamrs.recommendations.PersonalityGenresRecommender;
import it.uniroma3.sii.javamrs.recommendations.PersonalityNearestNeighborRecommender;
import it.uniroma3.sii.javamrs.recommendations.PreferenceRecommender;
import it.uniroma3.sii.javamrs.utils.textprocessing.cleaner.Parsing;
import it.uniroma3.sii.javamrs.utils.textprocessing.cleaner.TextCleaner;
import it.uniroma3.sii.javamrs.utils.textprocessing.stemmer.SnowballStemmer;
import it.uniroma3.sii.javamrs.utils.textprocessing.stemmer.italianStemmer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class RecommendationBean {
	
	private List<UserEntity> users;
	private RecommendationEntity recommendation;
	private List<TrackEntity> recTracks;
	private List<String> recGenres;
	
	private List<TrackEntity> favoriteTracks;
	private List<Friend> userFriends;
	private List<Post> userPosts;
	
	private Big5Entity big5Test;
	
	//User facade
	@EJB(beanName="userFacade")
	private UserFacade facade;
		
	//Bean di sessione
	@ManagedProperty(value="#{userSessionBean}")
	private UserSessionBean sessionBean;
	
	//Actions
	public String recommendation() {
		String recType = this.sessionBean.getRecType();
		String methodName = "recommend" + recType;
		
		String outcome = null;
		try {
			outcome = (String) this.getClass().getMethod(methodName).invoke(this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return outcome;
	}
	
	//Recommendation types
	public String recommendNeighbor() {
		//Carica gli utenti
		this.users = this.facade.retrieveAllUsers();
		List<TrackEntity> recommended = 
				new PersonalityNearestNeighborRecommender(this.sessionBean.getCurrentUser(), this.users).recommend();
		
		this.facade.addRecommendation(this.sessionBean.getCurrentUser(), recommended);
		
		return "recommendedTracks";
	}
	
	public String recommendGenre() {
		List<TrackEntity> recommended = 
				new PersonalityGenresRecommender(this.sessionBean.getCurrentUser()).recommend();
		this.facade.addRecommendation(this.sessionBean.getCurrentUser(), recommended);
		return "recommendedTracksByGenre";
	}
	
	public String recommendPreference() {
		List<TrackEntity> recommended =
				new PreferenceRecommender(this.sessionBean.getCurrentUser(), this.favoriteTracks).recommend();
		
		this.facade.addRecommendation(this.sessionBean.getCurrentUser(), recommended);
		return "recommendedTracksByPref";
	}
	
	public String recommendFacebook() {
		Facebook fbAccess = this.sessionBean.getFacebookAccess();
		
		List<Post> first100Posts = null;
		try {
			first100Posts = fbAccess.getPosts(new Reading().limit(100).fields("message"));
			System.out.println(first100Posts);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore Facebook");
		}
		TextCleaner cleaner = new TextCleaner(Parsing.ruleParser("normRules.txt", "="));
		SnowballStemmer stemmer = (SnowballStemmer) new italianStemmer();
		PostPersonalityPredict predictor = new PostPersonalityPredict(cleaner, stemmer);
		
		this.big5Test = predictor.makePrediction(first100Posts);
		return "recommendedTracksFacebook";
	}
		
	//Getters & Setters
	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public RecommendationEntity getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(RecommendationEntity recommendation) {
		this.recommendation = recommendation;
	}

	public UserFacade getFacade() {
		return facade;
	}

	public void setFacade(UserFacade facade) {
		this.facade = facade;
	}

	public UserSessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(UserSessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public List<TrackEntity> getRecTracks() {
		return recTracks;
	}

	public void setRecTracks(List<TrackEntity> recTracks) {
		this.recTracks = recTracks;
	}

	public List<String> getRecGenres() {
		return recGenres;
	}

	public void setRecGenres(List<String> recGenres) {
		this.recGenres = recGenres;
	}

	public List<TrackEntity> getFavoriteTracks() {
		return favoriteTracks;
	}

	public void setFavoriteTracks(List<TrackEntity> favoriteTracks) {
		this.favoriteTracks = favoriteTracks;
	}

	public List<Friend> getUserFriends() {
		return userFriends;
	}

	public void setUserFriends(List<Friend> userFriends) {
		this.userFriends = userFriends;
	}

	public List<Post> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(List<Post> userPosts) {
		this.userPosts = userPosts;
	}

	public Big5Entity getBig5Test() {
		return big5Test;
	}

	public void setBig5Test(Big5Entity big5Test) {
		this.big5Test = big5Test;
	}
}
