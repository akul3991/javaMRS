package recommendations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONException;

import LastFM.functions_lastFM;
import Objects.TrackEntity;
import it.uniroma3.connection.DBfunctions;

public class functions_rec {
	//Coseno similarità
	public double simp(List<Double> pu,List<Double> pv){
        double num = 0;
        double den = 0;
        double den1 = 0;
        double den2 = 0;
        for (int k=0;k<5;k++){
            num = num +(pu.indexOf(k)*pv.indexOf(k));
            den1 = den1 + (pu.indexOf(k)*pu.indexOf(k));
            den2 = den2 + (pv.indexOf(k)*pv.indexOf(k));
        }
        den = Math.sqrt(den1)*Math.sqrt(den2);
        
        return num/den;
    }
	//I 5 utenti più vicini
	public List<String> findNeighborhood(String ID){
		DBfunctions fun= new DBfunctions();
		List<Double> pu=fun.getBig5(ID);
		List<String> users=fun.findID();
		List<Double> pv=new ArrayList<Double>();
		Map<Double,String> map= new TreeMap<Double,String>();
		double simp;
		for (String u : users){
			if(u!=ID){
				pv=fun.getBig5(u);
				simp=simp(pu,pv);
				map.put(simp,u);
			}
		}
		int n=0;
		List<String> lista=new ArrayList<String>();
		for(String s: map.values()){
			if(n<5){
				lista.add(s);
				n++;
			}
			else
				break;
		}
		return lista;
	}
	//Lista delle canzoni preferite dai 5 utenti più vicini e inserimento come preferite
	public List<String> findRecommendations(String ID){
		List<String> vicini=new ArrayList<String>();
		vicini=findNeighborhood(ID);
		DBfunctions fun=new DBfunctions();
		List<String> canzoni=new ArrayList<String>();
		for(String user:vicini){
			if(fun.ExistRaccomandation(user))
				canzoni.addAll(fun.getTrackUser(user).getTrackID());	
		}
		fun.insertRecTracks(ID, canzoni);
		System.out.println("ID"+"   Ciaociao");
		return canzoni;
	}
	
	public boolean trackIsNotRecommended(List<TrackEntity> lista, String traccia, String artista){
		for(TrackEntity track: lista){
			if(track.getTitle()==traccia && track.getArtist()==artista)
					return false;
			}
		return true;
	}
	
	public List<TrackEntity> findNextRecommendation(String user) throws IOException, JSONException{
		DBfunctions fun=new DBfunctions();
		functions_lastFM f=new functions_lastFM();
		List<TrackEntity> lista=fun.takeTracks(user);
		List<TrackEntity> nuova= new ArrayList<TrackEntity>();
		int c=0;
		for(TrackEntity track:lista){
			List<TrackEntity> simil=f.getTrackSimilar(track.getArtist(), track.getTitle());
			for(TrackEntity si:simil){
				if(trackIsNotRecommended(lista,si.getTitle(),si.getArtist())){
					nuova.add(si);
					fun.addTrack(si.getTitle(), si.getArtist());
				}
			}
			c++;
			if(c>4)
				break;
		}
		for(TrackEntity tracks:nuova){
			fun.insertTrack(user, tracks.getID());
		}
		return nuova;
	}
}
