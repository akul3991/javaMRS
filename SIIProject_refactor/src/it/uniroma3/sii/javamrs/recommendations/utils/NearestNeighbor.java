package it.uniroma3.sii.javamrs.recommendations.utils;

import it.uniroma3.sii.javamrs.model.Big5Entity;
import it.uniroma3.sii.javamrs.model.TrackEntity;
import it.uniroma3.sii.javamrs.model.UserEntity;
import it.uniroma3.sii.javamrs.model.functions.FunctionsLastFM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONException;

public class NearestNeighbor {
	
	private static double simp(Big5Entity pu, Big5Entity pv) {
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
	
	public static List<UserEntity> findNeighborhood(UserEntity target, List<UserEntity> userSet, int neighSize) {
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
			if (n < neighSize) {
				neighbors.add(ue);
				n += 1;
			}
			else break;
		}
		return neighbors;
	}
}
