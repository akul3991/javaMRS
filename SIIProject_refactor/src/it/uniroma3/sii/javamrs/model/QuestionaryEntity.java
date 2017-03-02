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

public class QuestionaryEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String sex;
	private Integer age;
	private String nationality;
	private String work;
	private String education;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="questionary_id")
	private List<QuestionaryValueEntity> values = new ArrayList<>();

	public QuestionaryEntity() {
		super();
	}
	
	public QuestionaryEntity(String sex, Integer age, String nationality, String work, String education) {
		this.sex = sex;
		this.age = age;
		this.nationality = nationality;
		this.work = work;
		this.education = education;
	}
	
	public QuestionaryEntity(String sex, Integer age, String nationality, String work, String education,
			                 List<QuestionaryValueEntity> values) {
		this.sex = sex;
		this.age = age;
		this.nationality = nationality;
		this.work = work;
		this.education = education;
		this.values = values;
	}
	
	public QuestionaryEntity(String ID, String Sex, Integer Age, String Nationality, String Work, String Education,
			                 List<QuestionaryValueEntity> values) {
		this.id = ID;
		this.sex = Sex;
		this.age = Age;
		this.nationality = Nationality;
		this.work = Work;
		this.education = Education;
		this.values = values;
	}

	public String getId() {
		return id;
	}

	public void setId(String iD) {
		this.id = iD;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public List<QuestionaryValueEntity> getValues() {
		return this.values;
	}

	public void setValues(List<QuestionaryValueEntity> values) {
		this.values = values;
	}
}
