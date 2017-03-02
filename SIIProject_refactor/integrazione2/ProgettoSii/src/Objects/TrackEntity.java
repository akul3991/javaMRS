package Objects;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TrackEntity
 *
 */
@Entity

public class TrackEntity implements Serializable {

	   
	@Id
	private String ID;
	private String Title;
	private String Artist;
	private static final long serialVersionUID = 1L;

	public TrackEntity() {
		super();
	}   
	public TrackEntity(String ID,String Title, String Artist){
		this.ID=ID;
		this.Title=Title;
		this.Artist=Artist;
	}
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}   
	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}   
	public String getArtist() {
		return this.Artist;
	}

	public void setArtist(String Artist) {
		this.Artist = Artist;
	}
   
}
