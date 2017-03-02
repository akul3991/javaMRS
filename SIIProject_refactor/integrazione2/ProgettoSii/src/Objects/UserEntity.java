package Objects;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserEntity
 *
 */
@Entity

public class UserEntity implements Serializable {

	   
	@Id
	private String ID;
	private String Name;
	private String Email;
	private String UID;
	private static final long serialVersionUID = 1L;

	public UserEntity() {
		super();
	}   
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}   
	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}   
	public String getEmail() {
		return this.Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}   
	public String getUID() {
		return this.UID;
	}

	public void setUID(String UID) {
		this.UID = UID;
	}
   
}
