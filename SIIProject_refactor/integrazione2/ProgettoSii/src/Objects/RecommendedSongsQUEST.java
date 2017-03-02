package Objects;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: RecommendedSongsQUEST
 *
 */
@Entity

public class RecommendedSongsQUEST implements Serializable {

	   
	@Id
	private String ID;
	private List<String> TrackID;
	private static final long serialVersionUID = 1L;

	public RecommendedSongsQUEST() {
		super();
	}   
	
	public RecommendedSongsQUEST(String ID){
		this.ID=ID;
		this.TrackID= new ArrayList<String>();
	}
	
	public RecommendedSongsQUEST(String ID, List<String> TrackID){
		this.ID=ID;
		this.TrackID= TrackID;
	}
	
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}   
	public List<String> getTrackID() {
		return this.TrackID;
	}

	public void setTrackID(String Track) {
		this.TrackID.add(Track);
	}
   
}
