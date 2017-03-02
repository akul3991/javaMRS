package Objects;

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
	private String ID;
	private double Ape;
	private double Grad;
	private double Cosc;
	private double Nevr;
	private double Estr;
	private static final long serialVersionUID = 1L;

	public Big5Entity() {
		super();
	}   
	
	public Big5Entity(String ID, double Ape,double Grad, double Cosc, double Nevr, double Estr){
		this.ID=ID;
		this.Ape=Ape;
		this.Grad=Grad;
		this.Cosc=Cosc;
		this.Nevr=Nevr;
		this.Estr=Estr;
	}
	
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}   
	public double getApe() {
		return this.Ape;
	}

	public void setApe(double Ape) {
		this.Ape = Ape;
	}   
	public double getGrad() {
		return this.Grad;
	}

	public void setGrad(double Grad) {
		this.Grad = Grad;
	}   
	public double getCosc() {
		return this.Cosc;
	}

	public void setCosc(double Cosc) {
		this.Cosc = Cosc;
	}   
	public double getNevr() {
		return this.Nevr;
	}

	public void setNevr(double Nevr) {
		this.Nevr = Nevr;
	}   
	public double getEstr() {
		return this.Estr;
	}

	public void setEstr(double Estr) {
		this.Estr = Estr;
	}
   
}
