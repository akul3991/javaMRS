package it.uniroma3.sii.javamrs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionaryValueEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer qIndex;
	private Integer value;
	
	public QuestionaryValueEntity() {
		super();
	}
	
	public QuestionaryValueEntity(Integer qIndex, Integer value) {
		this.qIndex = qIndex;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getqIndex() {
		return qIndex;
	}

	public void setqIndex(Integer qIndex) {
		this.qIndex = qIndex;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
