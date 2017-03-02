package it.uniroma3.sii.javamrs.controller;

import it.uniroma3.sii.javamrs.controller.facade.UserFacade;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(eager=true)
public class FinalSurveyBean {
	
	private final Integer NO_OF_QUESTIONS = 5;
	
	//Bean di sessione
	@ManagedProperty(value="#{userSessionBean}")
	private UserSessionBean sessionBean;
	
	//User facade
	@EJB(beanName="userFacade")
	private UserFacade userFacade;
	
	private String feedback;
	private String recEvaluated;
	
	//Valori della valutazione finale
	private Integer[] values;
	
	public FinalSurveyBean() {}
	
	@PostConstruct
	public void init() {
		this.values = new Integer[NO_OF_QUESTIONS];
		//Il valore di default è 1 per tutte le risposte
		Arrays.fill(this.values, 1);
	}
	
	//Actions
	public String submit() {
		this.recEvaluated = this.sessionBean.getRecType();
		
		this.userFacade.addEvaluation(this.sessionBean.getCurrentUser(), this.feedback, this.recEvaluated, this.values);
		
		return "userHome";
	}

	
	//Getters & Setters
	public UserSessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(UserSessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public Integer[] getValues() {
		return values;
	}

	public void setValues(Integer[] values) {
		this.values = values;
	}

	public Integer getNO_OF_QUESTIONS() {
		return NO_OF_QUESTIONS;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	public String getRecEvaluated() {
		return recEvaluated;
	}

	public void setRecEvaluated(String recEvaluated) {
		this.recEvaluated = recEvaluated;
	}
}
