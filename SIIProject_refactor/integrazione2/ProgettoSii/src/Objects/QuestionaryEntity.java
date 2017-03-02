package Objects;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: QuestionaryEntity
 *
 */
@Entity

public class QuestionaryEntity implements Serializable {

	   
	@Id
	private String ID;
	private String Sex;
	private int Age;
	private String Nationality;
	private String Work;
	private String Education;
	private int q1;
	private int q2;
	private int q3;
	private int q4;
	private int q5;
	private int q6;
	private int q7;
	private int q8;
	private int q9;
	private int q10;
	private int q11;
	private int q12;
	private int q13;
	private int q14;
	private int q15;
	private int q16;
	private int q17;
	private int q18;
	private int q19;
	private int q20;
	private int q21;
	private int q22;
	private int q23;
	private int q24;
	private int q25;
	private int q26;
	private int q27;
	private int q28;
	private int q29;
	private int q30;
	private int q31;
	private int q32;
	private int q33;
	private int q34;
	private int q35;
	private int q36;
	private int q37;
	private int q38;
	private int q39;
	private int q40;
	private int q41;
	private int q42;
	private int q43;
	private int q44;
	private static final long serialVersionUID = 1L;

	public QuestionaryEntity() {
		super();
	}   
	public QuestionaryEntity(String ID, String Sex, int Age, String Nationality, String Work, 
			String Education, int q1, int q2, int q3, int q4, int q5, int q6, int q7, int q8, 
			int q9, int q10, int q11, int q12, int q13, int q14, int q15, int q16, int q17, 
			int q18, int q19, int q20, int q21, int q22, int q23, int q24, int q25, int q26,
			int q27, int q28, int q29, int q30, int q31, int q32, int q33, int q34, int q35,
			int q36, int q37, int q38, int q39, int q40, int q41, int q42,int q43, int q44){
		this.ID=ID;
		this.Sex=Sex;
		this.Age=Age;
		this.Nationality=Nationality;
		this.Work=Work;
		this.Education=Education;
		this.q1=q1;
		this.q2=q2;
		this.q3=q3;
		this.q4=q4;
		this.q5=q5;
		this.q6=q6;
		this.q7=q7;
		this.q8=q8;
		this.q9=q9;
		this.q10=q10;
		this.q11=q11;
		this.q12=q12;
		this.q13=q13;
		this.q14=q14;
		this.q15=q15;
		this.q16=q16;
		this.q17=q17;
		this.q18=q18;
		this.q19=q19;
		this.q20=q20;
		this.q21=q21;
		this.q22=q22;
		this.q23=q23;
		this.q24=q24;
		this.q25=q25;
		this.q26=q26;
		this.q27=q27;
		this.q28=q28;
		this.q29=q29;
		this.q30=q30;
		this.q31=q31;
		this.q32=q32;
		this.q33=q33;
		this.q34=q34;
		this.q35=q35;
		this.q36=q36;
		this.q37=q37;
		this.q38=q38;
		this.q39=q39;
		this.q40=q40;
		this.q41=q41;
		this.q42=q42;
		this.q43=q43;
		this.q44=q44;
		
	}
	
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}   
	public String getSex() {
		return this.Sex;
	}

	public void setSex(String Sex) {
		this.Sex = Sex;
	}   
	public int getAge() {
		return this.Age;
	}

	public void setAge(int Age) {
		this.Age = Age;
	}   
	public String getNationality() {
		return this.Nationality;
	}

	public void setNationality(String Nationality) {
		this.Nationality = Nationality;
	}   
	public String getWork() {
		return this.Work;
	}

	public void setWork(String Work) {
		this.Work = Work;
	}   
	public String getEducation() {
		return this.Education;
	}

	public void setEducation(String Education) {
		this.Education = Education;
	}   
	public int getQ1() {
		return this.q1;
	}

	public void setQ1(int q1) {
		this.q1 = q1;
	}   
	public int getQ2() {
		return this.q2;
	}

	public void setQ2(int q2) {
		this.q2 = q2;
	}   
	public int getQ3() {
		return this.q3;
	}

	public void setQ3(int q3) {
		this.q3 = q3;
	}   
	public int getQ4() {
		return this.q4;
	}

	public void setQ4(int q4) {
		this.q4 = q4;
	}   
	public int getQ5() {
		return this.q5;
	}

	public void setQ5(int q5) {
		this.q5 = q5;
	}   
	public int getQ6() {
		return this.q6;
	}

	public void setQ6(int q6) {
		this.q6 = q6;
	}   
	public int getQ7() {
		return this.q7;
	}

	public void setQ7(int q7) {
		this.q7 = q7;
	}   
	public int getQ8() {
		return this.q8;
	}

	public void setQ8(int q8) {
		this.q8 = q8;
	}   
	public int getQ9() {
		return this.q9;
	}

	public void setQ9(int q9) {
		this.q9 = q9;
	}   
	public int getQ10() {
		return this.q10;
	}

	public void setQ10(int q10) {
		this.q10 = q10;
	}   
	public int getQ11() {
		return this.q11;
	}

	public void setQ11(int q11) {
		this.q11 = q11;
	}   
	public int getQ12() {
		return this.q12;
	}

	public void setQ12(int q12) {
		this.q12 = q12;
	}   
	public int getQ13() {
		return this.q13;
	}

	public void setQ13(int q13) {
		this.q13 = q13;
	}   
	public int getQ14() {
		return this.q14;
	}

	public void setQ14(int q14) {
		this.q14 = q14;
	}   
	public int getQ15() {
		return this.q15;
	}

	public void setQ15(int q15) {
		this.q15 = q15;
	}   
	
	public int getQ16() {
		return this.q16;
	}

	public void setQ16(int q16) {
		this.q16 = q16;
	}   
	public int getQ17() {
		return this.q17;
	}

	public void setQ17(int q17) {
		this.q17 = q17;
	}   
	public int getQ18() {
		return this.q18;
	}

	public void setQ18(int q18) {
		this.q18 = q18;
	}   
	public int getQ19() {
		return this.q19;
	}

	public void setQ19(int q19) {
		this.q19 = q19;
	}   
	public int getQ20() {
		return this.q20;
	}

	public void setQ20(int q20) {
		this.q20 = q20;
	}   
	public int getQ21() {
		return this.q21;
	}

	public void setQ21(int q21) {
		this.q21 = q21;
	}   
	public int getQ22() {
		return this.q22;
	}

	public void setQ22(int q22) {
		this.q22 = q22;
	}   
	public int getQ23() {
		return this.q23;
	}

	public void setQ23(int q23) {
		this.q23 = q23;
	}   
	public int getQ24() {
		return this.q24;
	}

	public void setQ24(int q24) {
		this.q24 = q24;
	}   
	public int getQ25() {
		return this.q25;
	}

	public void setQ25(int q25) {
		this.q25 = q25;
	}   
	public int getQ26() {
		return this.q26;
	}

	public void setQ26(int q26) {
		this.q26 = q26;
	}   
	public int getQ27() {
		return this.q27;
	}

	public void setQ27(int q27) {
		this.q27 = q27;
	}   
	public int getQ28() {
		return this.q28;
	}

	public void setQ28(int q28) {
		this.q28 = q28;
	}   
	public int getQ29() {
		return this.q29;
	}

	public void setQ29(int q29) {
		this.q29 = q29;
	}   
	public int getQ30() {
		return this.q30;
	}

	public void setQ30(int q30) {
		this.q30 = q30;
	}   
	public int getQ31() {
		return this.q31;
	}

	public void setQ31(int q31) {
		this.q31 = q31;
	}   
	public int getQ32() {
		return this.q32;
	}

	public void setQ32(int q32) {
		this.q32 = q32;
	}   
	public int getQ33() {
		return this.q33;
	}

	public void setQ33(int q33) {
		this.q33 = q33;
	}   
	public int getQ34() {
		return this.q34;
	}

	public void setQ34(int q34) {
		this.q34 = q34;
	}   
	public int getQ35() {
		return this.q35;
	}

	public void setQ35(int q35) {
		this.q35 = q35;
	}   
	public int getQ36() {
		return this.q36;
	}

	public void setQ36(int q36) {
		this.q36 = q36;
	}   
	public int getQ37() {
		return this.q37;
	}

	public void setQ37(int q37) {
		this.q37 = q37;
	}   
	public int getQ38() {
		return this.q38;
	}

	public void setQ38(int q38) {
		this.q38 = q38;
	}   
	public int getQ39() {
		return this.q39;
	}

	public void setQ39(int q39) {
		this.q39 = q39;
	}   
	public int getQ40() {
		return this.q40;
	}

	public void setQ40(int q40) {
		this.q40 = q40;
	}   
	public int getQ41() {
		return this.q41;
	}

	public void setQ41(int q41) {
		this.q41 = q41;
	}   
	public int getQ42() {
		return this.q42;
	}

	public void setQ42(int q42) {
		this.q42 = q42;
	}   
	public int getQ43() {
		return this.q43;
	}

	public void setQ43(int q43) {
		this.q43 = q43;
	}   
	public int getQ44() {
		return this.q44;
	}

	public void setQ44(int q44) {
		this.q44 = q44;
	}
   
}
