package it.uniroma3.sii.javamrs.model.functions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONException;

import it.uniroma3.sii.javamrs.model.Big5Entity;
import it.uniroma3.sii.javamrs.model.TrackEntity;
import it.uniroma3.sii.javamrs.model.UserEntity;

public class FunctionsRec {
	
	public static double simp(Big5Entity pu, Big5Entity pv) {
		double num = 0;
		double den = 0;
		double den1 = 0;
		double den2 = 0;
		
		double[] puVector = pu.vectorize();
		double[] pvVector = pv.vectorize();
		
		for (int k = 0; k < 5; k += 1) {
			num = num + (puVector[k] * pvVector[k]);
			den1 = den1 + (puVector[k] * puVector[k]);
			den2 = den2 + (pvVector[k] * pvVector[k]);
		}
		den = Math.sqrt(den1) * Math.sqrt(den2);
		
		return num/den;
	}
	
	public static List<TrackEntity> findRecommendations(UserEntity target, List<UserEntity> userSet) {
		List<UserEntity> neighbors = findNeighborhood(target, userSet);
		List<TrackEntity> canzoni = new ArrayList<>();
		
		for (UserEntity neighbor : neighbors) {
			if (neighbor.hasRecommendation()) {
				List<TrackEntity> shuffledRecs = new ArrayList<>(neighbor.getRecommendationEntity().getRecTracks());
				Collections.shuffle(shuffledRecs);
				List<TrackEntity> filtered = shuffledRecs.subList(0, Math.min(3, shuffledRecs.size()));
				
				for (TrackEntity recommended : filtered) {
					canzoni.add(new TrackEntity(recommended.getArtist(), recommended.getTitle()));
				}
			}
		}
		return canzoni;
	}
	
	public static List<UserEntity> findNeighborhood(UserEntity target, List<UserEntity> userSet) {
		Big5Entity targetPers = target.getBig5Entity();
		Map<Double, UserEntity> similarity2User = new TreeMap<>();
		for (UserEntity u : userSet) {
			if (u.getID() != target.getID()) {
				Big5Entity uPers = u.getBig5Entity();
				Double simp = simp(targetPers, uPers);
				similarity2User.put(simp, u);
			}
		}
		int n = 0;
		List<UserEntity> neighbors = new ArrayList<>();
		for (UserEntity ue : similarity2User.values()) {
			if (n < 5) {
				neighbors.add(ue);
				n += 1;
			}
			else break;
		}
		
		return neighbors;
	}
	
	public static List<TrackEntity> findRecommendationsPreference(List<TrackEntity> favored) throws IOException, JSONException {
		List<TrackEntity> recommendation = new ArrayList<>();
		int limit = 15;
		for (TrackEntity fav : favored) {
			if (recommendation.size() < limit) {
				List<TrackEntity> similars = FunctionsLastFM.getTrackSimilar(fav.getArtist(), fav.getTitle());
				if (similars.size() < 4) {
					recommendation.addAll(similars);
				}
				else {
					recommendation.addAll(similars.subList(0, 4));
				}
			}
			else break;
		}
		return recommendation;
	}
	
//    function findNextRecommendations($recSongs, $FB, $CLASSIC){
//        $recommendation = array();
//        $cont=0;
//        $n=2*(ceil(10/sizeof($recSongs)));
//        foreach($recSongs as $track){
//            if($cont<15){
//                if($CLASSIC)
//                    $similarsSongs = getSimilarsNoImage($track[0], $track[1], $n);
//                else
//                    $similarsSongs = getSimilarsNoImage($track[0], $track[1], 2);
//
//                foreach(array_keys($similarsSongs) as $similarArtist){
//                    $similarArtist = $similarArtist;
//                    $title = $similarsSongs[$similarArtist];
//                    if ((trackISNOTrecommended($recSongs, $similarArtist, $title)) && !(array_key_exists($similarArtist, $recommendation))) {
//                        //$title = preg_replace("/\(.*\)/", "", $title);
//                        $recommendation[$similarArtist]=$title;
//                        $cont++;
//                    }
//                }
//            }else{
//                break;
//            }
//        }
//        $output = array_slice($recommendation, 0, 15);
//        foreach(array_keys($output) as $value){
//            $trackID = md5($output[$value].$value);
//            $artistID = md5($value);
//            addTracksAndArtist($artistID, $value, $trackID, $output[$value]);
//            if(!$CLASSIC)
//                addRecommendedSongs($trackID, $_SESSION['UID'], $FB);
//            else
//                addLibrary($_SESSION['UID'], $trackID);
//        }
//        return $output;
//    }
}
