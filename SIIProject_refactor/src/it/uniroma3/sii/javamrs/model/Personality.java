package it.uniroma3.sii.javamrs.model;

public class Personality {
	
	private Double extraversion;
	private Double openness;
	private Double agreableness;
	private Double consciousness;
	private Double neuroticism;
	
	public Personality(Double ext, Double ope, Double agr, Double con, Double neu) {
		this.extraversion = ext;
		this.openness = ope;
		this.agreableness = agr;
		this.consciousness = con;
		this.neuroticism = neu;
	}
	
	//Getters & Setters
	public Double getExtraversion() {
		return extraversion;
	}

	public void setExtraversion(Double extraversion) {
		this.extraversion = extraversion;
	}

	public Double getOpenness() {
		return openness;
	}

	public void setOpenness(Double openness) {
		this.openness = openness;
	}

	public Double getAgreableness() {
		return agreableness;
	}

	public void setAgreableness(Double agreableness) {
		this.agreableness = agreableness;
	}

	public Double getConsciousness() {
		return consciousness;
	}

	public void setConsciousness(Double consciousness) {
		this.consciousness = consciousness;
	}

	public Double getNeuroticism() {
		return neuroticism;
	}

	public void setNeuroticism(Double neuroticism) {
		this.neuroticism = neuroticism;
	}
}
