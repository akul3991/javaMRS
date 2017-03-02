package it.uniroma3.sii.javamrs.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(eager=true)
@ApplicationScoped
public class Questions {
	
	private String[] questionArray =
		{"è loquace", "tende a trovare da ridire sugli altri",
		 "lavora in modo accurato", "è depressa, triste",
		 "è originale, propone idee nuove", "è riservata",
		 "è premurosa e altruista con gli altri", "può essere piuttosto sbadata",
		 "è rilassata, gestisce bene lo stress", "ha curiosità in molti ambiti diversi",
		 "è piena di energia", "attacca briga con gli altri",
		 "è un lavoratore affidabile", "può essere tesa",
		 "è ingegnosa, un pensatore profondo", "genera molto entusiasmo",
		 "per natura tende a perdonare", "tende ad essere disorganizzata",
		 "si preoccupa molto", "ha un'immaginazione attiva",
		 "tende ad essere taciturna", "di solito si fida",
		 "tende ad essere pigra", "è emotivamente stabile, non si turba facilmente",
		 "è inventiva", "ha una personalità energica",
		 "può essere fredda ed emotivamente distaccata", "persevera finchè il compito è terminato",
		 "può essere lunatica", "apprezza le esperienze artistiche, estetiche",
		 "è qualche volta timida, inibita", "è premurosa e gentile pressochè con tutti",
		 "fa le cose efficientemente", "rimane calma nelle situazioni tese",
		 "preferisce un lavoro che sia di routine", "è estroversa, socievole",
		 "è qualche volta scortese con gli altri", "fa dei piani e li porta a termine",
		 "diventa facilmente apprensiva", "ama riflettere, giocare con le idee",
		 "ha pochi interessi artistici", "ama cooperare con gli altri",
		 "è facilmente distratta", "ha una sensibilità raffinata per l'arte, la musica o la letteratura"};
	
	private String[] feedbackQuestionArray = {
			"Ho trovato nuove canzoni di artisti a me già noti",
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
