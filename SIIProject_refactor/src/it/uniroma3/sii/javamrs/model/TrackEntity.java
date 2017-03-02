package it.uniroma3.sii.javamrs.model;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String ID;
	private String title;
	private String artist;
	private String url;
	private static final long serialVersionUID = 1L;

	public TrackEntity() {
		super();
	}
	
	public TrackEntity(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}
	
	public TrackEntity(String url, String title, String artist) {
		this.url = url;
		this.title = title;
		this.artist = artist;
	}
	
//	public TrackEntity(String ID,String Title, String Artist){
//		this.ID=ID;
//		this.title=Title;
//		this.artist=Artist;
//	}
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String Title) {
		this.title = Title;
	}   
	public String getArtist() {
		return this.artist;
	}

	public void setArtist(String Artist) {
		this.artist = Artist;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackEntity other = (TrackEntity) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
}
