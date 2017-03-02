package it.uniroma3.sii.javamrs.model;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: QuestionaryEntity
 *
 */
@Entity

public class EvaluationEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	private String feedback;
	private String recEvaluated;
	
	private Integer question1;
	private Integer question2;
	private Integer question3;
	private Integer question4;
	private Integer question5;

	public EvaluationEntity() {
		super();
	}
	
	public EvaluationEntity(String feedback, String recEvaluated, Integer[] questionValues) {
		this.feedback = feedback;
		this.recEvaluated = recEvaluated;
		this.question1 = questionValues[0];
		this.question2 = questionValues[1];
		this.question3 = questionValues[2];
		this.question4 = questionValues[3];
		this.question5 = questionValues[4];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Integer getQuestion1() {
		return question1;
	}

	public void setQuestion1(Integer question1) {
		this.question1 = question1;
	}

	public Integer getQuestion2() {
		return question2;
	}

	public void setQuestion2(Integer question2) {
		this.question2 = question2;
	}

	public Integer getQuestion3() {
		return question3;
	}

	public void setQuestion3(Integer question3) {
		this.question3 = question3;
	}

	public Integer getQuestion4() {
		return question4;
	}

	public void setQuestion4(Integer question4) {
		this.question4 = question4;
	}

	public Integer getQuestion5() {
		return question5;
	}

	public void setQuestion5(Integer question5) {
		this.question5 = question5;
	}

	public String getRecEvaluated() {
		return recEvaluated;
	}

	public void setRecEvaluated(String recEvaluated) {
		this.recEvaluated = recEvaluated;
	}
}
