package it.uniroma3.sii.javamrs.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Big5Entity
 *
 */
@Entity
public class Big5Entity implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String ID;
	private double ape;
	private double grad;
	private double cosc;
	private double nevr;
	private double estr;
	private static final long serialVersionUID = 1L;

	public Big5Entity() {
		super();
	}   
	
	public Big5Entity(double Ape, double Grad, double Cosc, double Nevr, double Estr){
		this.ape=Ape;
		this.grad=Grad;
		this.cosc=Cosc;
		this.nevr=Nevr;
		this.estr=Estr;
	}
	
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}   
	public double getApe() {
		return this.ape;
	}

	public void setApe(double Ape) {
		this.ape = Ape;
	}   
	public double getGrad() {
		return this.grad;
	}

	public void setGrad(double Grad) {
		this.grad = Grad;
	}   
	public double getCosc() {
		return this.cosc;
	}

	public void setCosc(double Cosc) {
		this.cosc = Cosc;
	}   
	public double getNevr() {
		return this.nevr;
	}

	public void setNevr(double Nevr) {
		this.nevr = Nevr;
	}   
	public double getEstr() {
		return this.estr;
	}

	public void setEstr(double Estr) {
		this.estr = Estr;
	}
	
	//Metodi di utilità
	public double[] vectorize() {
		return new double[] {this.ape, this.grad, this.cosc, this.nevr, this.estr};
	}
   
}
