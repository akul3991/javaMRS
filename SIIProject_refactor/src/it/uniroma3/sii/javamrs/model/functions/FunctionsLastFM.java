package it.uniroma3.sii.javamrs.model.functions;

import it.uniroma3.sii.javamrs.model.TrackEntity;
import it.uniroma3.sii.javamrs.utils.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FunctionsLastFM {
	
	public static List<TrackEntity> searchTrack(String trackQuery) throws IOException, JSONException {
		String requestUrl = audioscrobblerUrl("track.search", 3, "track=" + trackQuery);
		JSONObject obj = JsonReader.readJsonFromUrl(requestUrl);
		
		List<TrackEntity> lista = new ArrayList<TrackEntity>();
		JSONArray tracksFound = obj.getJSONObject("results")
				                   .getJSONObject("trackmatches")
				                   .getJSONArray("track");
		
		for (int i=0; i < tracksFound.length(); i += 1) {
			String name = tracksFound.getJSONObject(i).getString("name");
			String artist = tracksFound.getJSONObject(i).getString("artist");
			lista.add(new TrackEntity(name, artist));
		}
		
		return lista;
	}
			
	public static List<TrackEntity> getTrackBygenres(String genre) throws IOException, JSONException{
		JSONObject obj=JsonReader.readJsonFromUrl("http://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag="+genre+"&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=3");
		List<TrackEntity> lista=new ArrayList<TrackEntity>();
		for(int i=0;i<obj.getJSONObject("tracks").getJSONArray("track").length();i++){
			TrackEntity track= new TrackEntity();
			String name=obj.getJSONObject("tracks").getJSONArray("track").getJSONObject(i).getString("name");
			String artist=obj.getJSONObject("tracks").getJSONArray("track").getJSONObject(i).getJSONObject("artist").getString("name");
			track.setTitle(name);
			track.setArtist(artist);
			lista.add(track);
			//System.out.println("Canzone: "+name+"      Artista: "+artist+"     ID:"+id);
		}
		return lista;
	}
	
	public static List<TrackEntity> getTopTracks() throws IOException, JSONException {
		JSONObject obj = JsonReader.readJsonFromUrl
				("http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=50");
		List<TrackEntity> lista = new ArrayList<TrackEntity>();
		JSONArray trackArray = obj.getJSONObject("tracks").getJSONArray("track");
		for (int i=0; i < trackArray.length(); i += 1) {
			String name = trackArray.getJSONObject(i).getString("name");
			String artist = trackArray.getJSONObject(i).getJSONObject("artist").getString("name");
			lista.add(new TrackEntity(name, artist));
		}
		return lista;
	}
	
	public static List<TrackEntity> getTrackSimilar(String artist, String track) throws IOException, JSONException {
		String requestUrl = audioscrobblerUrl("track.getsimilar", 20, "artist=" + artist, "track=" + track);
		JSONObject obj = JsonReader.readJsonFromUrl(requestUrl);
		
		List<TrackEntity> lista = new ArrayList<TrackEntity>();
		JSONArray trackArray = obj.getJSONObject("similartracks").getJSONArray("track");
		for (int i=0; i < trackArray.length(); i += 1) {
			String trackName = trackArray.getJSONObject(i).getString("name");
			String trackArtist = trackArray.getJSONObject(i).getJSONObject("artist").getString("name");
			lista.add(new TrackEntity(trackName, trackArtist));
		}
		return lista;
	}
	
	public static TrackEntity getSingleTrackSimilar(String artist, String track) throws IOException, JSONException {
		String requestUrl = audioscrobblerUrl("track.getsimilar", 1, "artist=" + artist, "track=" + track);
		JSONObject obj = JsonReader.readJsonFromUrl(requestUrl);
		
		JSONArray trackArray = obj.getJSONObject("similartracks").getJSONArray("track");
		TrackEntity similarTrack = null;
		for (int i=0; i < trackArray.length(); i += 1) {
			String trackName = trackArray.getJSONObject(i).getString("name");
			String trackArtist = trackArray.getJSONObject(i).getJSONObject("artist").getString("name");
			similarTrack = new TrackEntity(trackName, trackArtist);
		}
		return similarTrack;
	}
	
	public static String getTrackSimilarTest(String artist, String track) {
		return audioscrobblerUrl("track.getsimilar", 20, "artist=" + artist, "track=" + track);
	}
		
	private static String audioscrobblerUrl(String method, Integer limit, String... params) {
		String asUrl = "http://ws.audioscrobbler.com/2.0/?";
		String methodUrl = "method=" + method + "&";
		String paramsUrl = String.join("&", params) + "&";
		String apikeyUrl = "api_key=9c5daa5cd358dac7398d78764413518c" + "&";
		String formatJsonUrl = "format=json" + "&";
		String limitUrl = "limit=" + limit;
		
		return asUrl + methodUrl + paramsUrl + apikeyUrl + formatJsonUrl + limitUrl;
	}
	
//    function getTrackSimilar($artist, $track){
//        $url = "http://ws.audioscrobbler.com/2.0/?method=track.getsimilar&artist=$artist&track=$track&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=20";
//        return curl($url);
//    }
}
