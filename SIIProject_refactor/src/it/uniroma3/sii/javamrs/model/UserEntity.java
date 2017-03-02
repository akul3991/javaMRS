package it.uniroma3.sii.javamrs.model;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: UserEntity
 *
 */

@Entity
@NamedQueries({
	@NamedQuery(name="user.getByEmail", query="SELECT u FROM UserEntity u WHERE u.email=:p_email"),
	@NamedQuery(name="user.getByUid", query="SELECT u FROM UserEntity u WHERE u.uid=:p_uid"),
	@NamedQuery(name="user.getAll", query="SELECT u FROM UserEntity u")
})
public class UserEntity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ID;
	private String name;
	private String email;
	private String uid;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Big5Entity big5Entity;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private QuestionaryEntity questionaryEntity;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private RecommendationEntity recommendationEntity;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<EvaluationEntity> evaluations = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	public UserEntity() {
		super();
	}
	
	public UserEntity(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public UserEntity(String name, String email, String uid) {
		this.name = name;
		this.email = email;
		this.uid = uid;
	}
	
	public UserEntity(String name, String email, String uid, Big5Entity big5) {
		this.name = name;
		this.email = email;
		this.uid = uid;
		this.big5Entity = big5;
	}
	
	public Integer getID() {
		return this.ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String Name) {
		this.name = Name;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String Email) {
		this.email = Email;
	}   
	public String getUid() {
		return this.uid;
	}

	public void setUid(String UID) {
		this.uid = UID;
	}

	public Big5Entity getBig5Entity() {
		return big5Entity;
	}

	public void setBig5Entity(Big5Entity big5Entity) {
		this.big5Entity = big5Entity;
	}

	public QuestionaryEntity getQuestionaryEntity() {
		return questionaryEntity;
	}

	public void setQuestionaryEntity(QuestionaryEntity questionaryEntity) {
		this.questionaryEntity = questionaryEntity;
	}
	
	public RecommendationEntity getRecommendationEntity() {
		return recommendationEntity;
	}

	public void setRecommendationEntity(RecommendationEntity recommendationEntity) {
		this.recommendationEntity = recommendationEntity;
	}
		
	public List<EvaluationEntity> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<EvaluationEntity> evaluations) {
		this.evaluations = evaluations;
	}
	
	public void addEvaluationEntity(EvaluationEntity eval) {
		this.evaluations.add(eval);
	}

	//Metodi di utilità
	public boolean hasRecommendation() {
		return this.getRecommendationEntity() != null;
	}
	
	public boolean hasQuestionnaire() {
		return this.getQuestionaryEntity() != null;
	}
}
