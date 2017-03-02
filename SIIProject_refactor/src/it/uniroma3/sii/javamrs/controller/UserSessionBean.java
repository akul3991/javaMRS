package it.uniroma3.sii.javamrs.controller;

import facebook4j.Facebook;
import it.uniroma3.sii.javamrs.controller.facade.UserFacade;
import it.uniroma3.sii.javamrs.model.UserEntity;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(eager=true)
@SessionScoped
public class UserSessionBean {
	
	private UserEntity currentUser;
	
	private Facebook facebookAccess;
	private String fbToken;
	private String recType;
	
	//Actions
	public String personalityLike() {
		if (this.facebookAccess == null) {
			return "noFacebook";
		}
		else {
			this.recType="Facebook";
			return "facebookRecommender";
		}
	}
	
	public String surveyGenreRecommendation() {
		this.recType = "Genre";
		return this.questionnaireExists();
	}
	
	public String surveyNeighborRecommendation() {
		this.recType = "Neighbor";
		return this.questionnaireExists();
	}
	
	public String preferenceRecommendation() {
		this.recType = "Preference";
		return "favoriteForm";
	}
	
	public String newQuestionnaire() {
		return "surveyBIG5";
	}
	
	public String evaluate() {
		return "surveyFinal";
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
	
	
	//Private methods
	private String questionnaireExists() {
		if (this.currentUser.hasQuestionnaire())
			return "qstResult";
		else
			return "surveyBIG5";
	}
	
	//Getters & Setters
	public UserEntity getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserEntity currentUser) {
		this.currentUser = currentUser;
	}

	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public String getFbToken() {
		return fbToken;
	}

	public void setFbToken(String fbToken) {
		this.fbToken = fbToken;
	}

	public Facebook getFacebookAccess() {
		return facebookAccess;
	}

	public void setFacebookAccess(Facebook facebookAccess) {
		this.facebookAccess = facebookAccess;
	}
}
