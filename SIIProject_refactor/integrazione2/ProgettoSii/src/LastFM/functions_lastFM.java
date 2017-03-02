package LastFM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import Objects.TrackEntity;
import it.uniroma3.connection.JsonReader;

public class functions_lastFM {
	public List<TrackEntity> getTopTrack() throws IOException, JSONException{
		JSONObject obj=JsonReader.readJsonFromUrl("http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=3");
		List<TrackEntity> lista=new ArrayList<TrackEntity>();
		for(int i=0;i<obj.getJSONObject("tracks").getJSONArray("track").length();i++){
			TrackEntity track= new TrackEntity();
			String name=obj.getJSONObject("tracks").getJSONArray("track").getJSONObject(i).getString("name");
			String artist=obj.getJSONObject("tracks").getJSONArray("track").getJSONObject(i).getJSONObject("artist").getString("name");
			String id=name.concat(artist);
			track.setID(id);
			track.setTitle(name);
			track.setArtist(artist);
			lista.add(track);
			//System.out.println("Canzone: "+name+"      Artista: "+artist+"     ID:"+id);
		}
		return lista;
	}
	
	public List<TrackEntity> getTrackSimilar(String artista,String traccia) throws IOException, JSONException{
		JSONObject obj=JsonReader.readJsonFromUrl("http://ws.audioscrobbler.com/2.0/?method=track.getsimilar&artist="+artista+"&track="+traccia+"&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=3");
		List<TrackEntity> lista=new ArrayList<TrackEntity>();
		for(int i=0;i<obj.getJSONObject("similartracks").getJSONArray("track").length();i++){
			TrackEntity track= new TrackEntity();
			String name=obj.getJSONObject("similartracks").getJSONArray("track").getJSONObject(i).getString("name");
			String artist=obj.getJSONObject("similartracks").getJSONArray("track").getJSONObject(i).getJSONObject("artist").getString("name");
			String id=name.concat(artist);
			track.setID(id);
			track.setTitle(name);
			track.setArtist(artist);
			lista.add(track);
			//System.out.println("Canzone: "+name+"      Artista: "+artist+"     ID:"+id);
		}
		return lista;
	}
	
	public List<TrackEntity> getArtistTopTracks(String artista) throws IOException, JSONException{
		JSONObject obj=JsonReader.readJsonFromUrl("http://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist="+artista+"&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=3");
		List<TrackEntity> lista=new ArrayList<TrackEntity>();
		for(int i=0;i<obj.getJSONObject("toptracks").getJSONArray("track").length();i++){
			TrackEntity track= new TrackEntity();
			String name=obj.getJSONObject("toptracks").getJSONArray("track").getJSONObject(i).getString("name");
			String artist=obj.getJSONObject("toptracks").getJSONArray("track").getJSONObject(i).getJSONObject("artist").getString("name");
			String id=name.concat(artist);
			track.setID(id);
			track.setTitle(name);
			track.setArtist(artist);
			lista.add(track);
			//System.out.println("Canzone: "+name+"      Artista: "+artist+"     ID:"+id);
		}
		return lista;
	}
	
	public List<TrackEntity> searchtrack(String traccia) throws IOException, JSONException{
		JSONObject obj=JsonReader.readJsonFromUrl("http://ws.audioscrobbler.com/2.0/?method=track.search&track="+traccia+"&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=3");
		List<TrackEntity> lista=new ArrayList<TrackEntity>();
		for(int i=0;i<obj.getJSONObject("results").getJSONObject("trackmatches").getJSONArray("track").length();i++){
			TrackEntity track= new TrackEntity();
			String name=obj.getJSONObject("results").getJSONObject("trackmatches").getJSONArray("track").getJSONObject(i).getString("name");
			String artist=obj.getJSONObject("results").getJSONObject("trackmatches").getJSONArray("track").getJSONObject(i).getString("artist");
			String id=name.concat(artist);
			track.setID(id);
			track.setTitle(name);
			track.setArtist(artist);
			lista.add(track);
			//System.out.println("Canzone: "+name+"      Artista: "+artist+"     ID:"+id);
		}
		return lista;
	}
	
	public List<TrackEntity> getTrackBygenres(String genre) throws IOException, JSONException{
		JSONObject obj=JsonReader.readJsonFromUrl("http://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag="+genre+"&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=3");
		List<TrackEntity> lista=new ArrayList<TrackEntity>();
		for(int i=0;i<obj.getJSONObject("tracks").getJSONArray("track").length();i++){
			TrackEntity track= new TrackEntity();
			String name=obj.getJSONObject("tracks").getJSONArray("track").getJSONObject(i).getString("name");
			String artist=obj.getJSONObject("tracks").getJSONArray("track").getJSONObject(i).getJSONObject("artist").getString("name");
			String id=name.concat(artist);
			track.setID(id);
			track.setTitle(name);
			track.setArtist(artist);
			lista.add(track);
			//System.out.println("Canzone: "+name+"      Artista: "+artist+"     ID:"+id);
		}
		return lista;
	}
}
