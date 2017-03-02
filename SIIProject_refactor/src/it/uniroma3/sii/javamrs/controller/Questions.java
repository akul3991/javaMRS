package it.uniroma3.sii.javamrs.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(eager=true)
@ApplicationScoped
public class Questions {
	
	private String[] questionArray =
		{"� loquace", "tende a trovare da ridire sugli altri",
		 "lavora in modo accurato", "� depressa, triste",
		 "� originale, propone idee nuove", "� riservata",
		 "� premurosa e altruista con gli altri", "pu� essere piuttosto sbadata",
		 "� rilassata, gestisce bene lo stress", "ha curiosit� in molti ambiti diversi",
		 "� piena di energia", "attacca briga con gli altri",
		 "� un lavoratore affidabile", "pu� essere tesa",
		 "� ingegnosa, un pensatore profondo", "genera molto entusiasmo",
		 "per natura tende a perdonare", "tende ad essere disorganizzata",
		 "si preoccupa molto", "ha un'immaginazione attiva",
		 "tende ad essere taciturna", "di solito si fida",
		 "tende ad essere pigra", "� emotivamente stabile, non si turba facilmente",
		 "� inventiva", "ha una personalit� energica",
		 "pu� essere fredda ed emotivamente distaccata", "persevera finch� il compito � terminato",
		 "pu� essere lunatica", "apprezza le esperienze artistiche, estetiche",
		 "� qualche volta timida, inibita", "� premurosa e gentile pressoch� con tutti",
		 "fa le cose efficientemente", "rimane calma nelle situazioni tese",
		 "preferisce un lavoro che sia di routine", "� estroversa, socievole",
		 "� qualche volta scortese con gli altri", "fa dei piani e li porta a termine",
		 "diventa facilmente apprensiva", "ama riflettere, giocare con le idee",
		 "ha pochi interessi artistici", "ama cooperare con gli altri",
		 "� facilmente distratta", "ha una sensibilit� raffinata per l'arte, la musica o la letteratura"};
	
	private String[] feedbackQuestionArray = {
			"Ho trovato nuove canzoni di artisti a me gi� noti",
			"Ho trovato canzoni di artisti che non conoscevo e che da ora vorrei inziare ad ascoltare",
			"Queste canzoni sono lontane da quelle che ascolto normalmente", 
			"Ho trovato interessante la playlist suggerita", 
			"Userei nuovamente questo suggeritore di playlist in futuro"
	};
	
	public Questions() {}

	//Getters & setters
	public String[] getQuestionArray() {
		return questionArray;
	}

	public void setQuestionArray(String[] questions) {}

	public String[] getFeedbackQuestionArray() {
		return feedbackQuestionArray;
	}

	public void setFeedbackQuestionArray(String[] feedbackQuestionArray) {}	
}
