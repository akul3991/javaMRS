package recommendations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import LastFM.functions_lastFM;
import Objects.TrackEntity;
import it.uniroma3.connection.DBfunctions;

public class personality_genres {
	public List<TrackEntity> out;

	public personality_genres(String ID) throws IOException, JSONException{
		DBfunctions fun=new DBfunctions();
		boolean quest=fun.checkQuestIsEmpty(ID);
		if(quest){
			System.out.println("Questionario da fare");
		}
		else{
			System.out.println("Questionario fatto");
		}
		quest=fun.hasUserSongs(ID);
		functions_rec f= new functions_rec();
		if(quest){
			System.out.println("L'utente ha canzoni salvate");
			List<TrackEntity>lista=f.findNextRecommendation(ID);
			this.out=lista;
		}
		else{
			System.out.println("L'utente non ha canzoni salvate");
			List<String> lista=f.findRecommendations(ID);
			functions_lastFM lfm=new functions_lastFM();
			List<TrackEntity> out=new ArrayList<TrackEntity>();
			for(String nome: lista ){
				out.addAll(lfm.searchtrack(nome));	
				}
			this.out=out;
		}
	}
	public List<TrackEntity> getOut(){
		return this.out;
	}
}
