package it.uniroma3.sii.javamrs.controller;

import it.uniroma3.sii.javamrs.controller.facade.UserFacade;
import it.uniroma3.sii.javamrs.model.Personality;
import it.uniroma3.sii.javamrs.model.QuestionaryEntity;
import it.uniroma3.sii.javamrs.model.QuestionaryValueEntity;
import it.uniroma3.sii.javamrs.model.personality.PersonalityCalc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(eager=true)
public class QuestionnaireBean {
	
	private final Integer NO_OF_QUESTIONS = 44;
	
	//Valori del questionario
	
	//Informazioni demografiche
	private Integer eta;
	private String sesso;
	private String nazionalita;
	private String professione;
	private String titoloStudio;
	
	//Bean di sessione
	@ManagedProperty(value="#{userSessionBean}")
	private UserSessionBean sessionBean;
		
	//User facade
	@EJB(beanName="userFacade")
	private UserFacade facade;
	
	//Valori del test della personalità
	private Integer[] testValues;
	
	//Risultato del calcolo della personalità
	private Personality computedPers;
	
	//Questionario da persistere
	private QuestionaryEntity questionary;
	
	public QuestionnaireBean() {}
	
	@PostConstruct
	public void init() {
		this.testValues = new Integer[NO_OF_QUESTIONS];
		
		if (this.sessionBean.getCurrentUser().hasQuestionnaire())
			this.takeFromSession();
		else {
			//Il valore di default è 1 per tutte le risposte
			Arrays.fill(this.testValues, 1);
		}
	}
	
	private void takeFromSession() {
		QuestionaryEntity old = this.sessionBean.getCurrentUser().getQuestionaryEntity();
		
		this.eta = old.getAge();
		this.sesso = old.getSex();
		this.nazionalita = old.getNationality();
		this.professione = old.getWork();
		this.titoloStudio = old.getEducation();
		
		int i = 0;
		for (QuestionaryValueEntity value : old.getValues()) {
			this.testValues[i] = value.getValue();
			i += 1;
		}
	}
	
	//Actions
	
	public String submit() throws Exception {
		//Cancella la precedente raccomandazione se presente
		if (this.sessionBean.getCurrentUser().hasRecommendation())
			this.facade.removeRecommendation(this.sessionBean.getCurrentUser());
		
		this.facade.addQuestionary(this.sessionBean.getCurrentUser(),
				                   this.sesso, this.eta, this.nazionalita, this.professione, this.titoloStudio, makeValues());
		
		Personality pers = this.calculatePersonality();
		
		this.facade.addBig5(this.sessionBean.getCurrentUser(),
				            pers.getOpenness(),
				            pers.getAgreableness(),
				            pers.getConsciousness(),
				            pers.getNeuroticism(),
				            pers.getExtraversion());
		
		return "qstResult";
	}
			
	private List<QuestionaryValueEntity> makeValues() {
		List<QuestionaryValueEntity> values = new ArrayList<>();
		for (int i = 0; i < NO_OF_QUESTIONS; i += 1) {
			values.add(new QuestionaryValueEntity(i, this.testValues[i]));
		}
		
		return values;
	}
		
	private Personality calculatePersonality() {
		return new PersonalityCalc(this.testValues).calculate();
	}
		
	public String submitQuestionnaire() throws Exception {
		this.computedPers = new PersonalityCalc(this.testValues).calculate();
		return "qstResult";
	}

	//Getters & Setters
	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public String getProfessione() {
		return professione;
	}

	public void setProfessione(String professione) {
		this.professione = professione;
	}

	public String getTitoloStudio() {
		return titoloStudio;
	}

	public void setTitoloStudio(String titoloStudio) {
		this.titoloStudio = titoloStudio;
	}

	public UserSessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(UserSessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public Integer[] getTestValues() {
		return testValues;
	}

	public void setTestValues(Integer[] testValues) {
		this.testValues = testValues;
	}

	public Personality getComputedPers() {
		return computedPers;
	}

	public void setComputedPers(Personality computedPers) {
		this.computedPers = computedPers;
	}

	public QuestionaryEntity getQuestionary() {
		return questionary;
	}

	public void setQuestionary(QuestionaryEntity questionary) {
		this.questionary = questionary;
	}

	public Integer getNO_OF_QUESTIONS() {
		return NO_OF_QUESTIONS;
	}

	public UserFacade getFacade() {
		return facade;
	}

	public void setFacade(UserFacade facade) {
		this.facade = facade;
	}
}
