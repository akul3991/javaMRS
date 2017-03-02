package it.uniroma3.sii.javamrs.controller;

import java.util.List;

import facebook4j.Facebook;
import facebook4j.Reading;
import facebook4j.User;
import it.uniroma3.sii.javamrs.controller.facade.UserFacade;
import it.uniroma3.sii.javamrs.model.Big5Entity;
import it.uniroma3.sii.javamrs.model.UserEntity;
import it.uniroma3.sii.javamrs.model.functions.FunctionsFacebook;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.NoResultException;

@ManagedBean
public class UserBean {
	
	private String name;
	private String email;
	private String userId;
	private boolean newUser;
	
	private String fbToken;
	
	@ManagedProperty(value="#{userSessionBean}")
	private UserSessionBean sessionBean;
	
	//Utente individuato/creato
	private UserEntity user;
	
	//Segnalazioni di errore
	private Boolean userNotFound;
	
	//Facade
	@EJB(beanName="userFacade")
	private UserFacade facade;
	
	//Actions
	public String manualAccess() {
		UserEntity registeredUser;
		if (newUser) {
			registeredUser = this.facade.insertUser(this.name, this.email);
		}
		else {
			List<UserEntity> result = this.facade.retrieveUserByEmail(this.email);
			if (result.isEmpty()) {
				this.userNotFound = true;
				return "login";
			}
			registeredUser = result.get(0);
		}
		this.sessionBean.setCurrentUser(registeredUser);
		//Per test
		this.facade.test();
		return "userHome";
	}
	
	public String facebookAccess() {
		UserEntity registeredUser = null;
		
		Facebook fb = null;
		try {
			fb = FunctionsFacebook.setupFacebook(this.fbToken);
			User fbMe = fb.getMe(new Reading().fields("name"));
			List<UserEntity> retrieved = this.facade.retrieveUserByEmail(facebookEmail(fbMe));
			if (retrieved.isEmpty()) {
				registeredUser = this.facade.insertUser(fbMe.getName(), facebookEmail(fbMe));
			}
			else {
				registeredUser = retrieved.get(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connessione a Facebook fallita");
		}
		this.sessionBean.setCurrentUser(registeredUser);
		this.sessionBean.setFacebookAccess(fb);
		
		this.facade.initializeLastFMData("big5users.csv", "preferences.csv");
		return "userHome";
	}
	
	private String facebookEmail(User user) {
		return user.getName().toLowerCase().replace(" ", "_") + "@fb.it";
	}
		
	//Getters & Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserFacade getFacade() {
		return facade;
	}

	public void setFacade(UserFacade facade) {
		this.facade = facade;
	}

	public boolean getNewUser() {
		return newUser;
	}

	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Boolean getUserNotFound() {
		return userNotFound;
	}

	public void setUserNotFound(Boolean userNotFound) {
		this.userNotFound = userNotFound;
	}

	public UserSessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(UserSessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public String getFbToken() {
		return fbToken;
	}

	public void setFbToken(String fbToken) {
		this.fbToken = fbToken;
	}
}
