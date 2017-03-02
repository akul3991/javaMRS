package it.uniroma3.sii.javamrs.model;

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

public class RecommendationEntity implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name="recommendation_id")
	private List<TrackEntity> recTracks;
	
	private static final long serialVersionUID = 1L;

	public RecommendationEntity() {
		super();
		this.recTracks = new ArrayList<>();
	}   
	
	public RecommendationEntity(List<TrackEntity> recTracks) {
		this.recTracks = recTracks;
	}

	public String getId() {
		return id;
	}

	public void setId(String iD) {
		id = iD;
	}

	public List<TrackEntity> getRecTracks() {
		return recTracks;
	}

	public void setRecTracks(List<TrackEntity> recTracks) {
		this.recTracks = recTracks;
	}
	
	public void addTrack(TrackEntity track) {
		this.recTracks.add(track);
	}
}
