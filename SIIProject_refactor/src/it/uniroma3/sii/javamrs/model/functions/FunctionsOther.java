package it.uniroma3.sii.javamrs.model.functions;

import it.uniroma3.sii.javamrs.model.TrackEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

public class FunctionsOther {
	
	public static List<TrackEntity> getSuggestions() throws IOException, JSONException {
		return FunctionsLastFM.getTopTracks();
	}
		
//    function getSimilarsNoImage($artist, $song, $n){
//        $suggestions = array();
//        $similars = getTrackSimilar($artist, $song);
//        $json_file = json_decode($similars, true);
//        if(isset($json_file['similartracks']) && isset($json_file['similartracks']['track'])){
//            $similartracks = $json_file['similartracks']['track'];
//            $contSong=0;
//            if(sizeof($similartracks)!=1){
//                foreach($similartracks as $track){
//                    if($contSong<=$n && sizeof($suggestions)!=$n){
//                        if(isset($track['name']) && isset($track['artist']['name'])) {
//                            $title = $track['name'];
//                            $artistN = $track['artist']['name'];
//                            $suggestions[$artistN]=$title;
//                            $contSong++;
//                        }
//                    }else{
//                        break;
//                    }
//                }
//            }
//        }
//        return $suggestions;
//    }
}
