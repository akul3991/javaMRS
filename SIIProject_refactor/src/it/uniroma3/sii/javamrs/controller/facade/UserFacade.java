package it.uniroma3.sii.javamrs.controller.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.uniroma3.sii.javamrs.model.Big5Entity;
import it.uniroma3.sii.javamrs.model.EvaluationEntity;
import it.uniroma3.sii.javamrs.model.QuestionaryEntity;
import it.uniroma3.sii.javamrs.model.QuestionaryValueEntity;
import it.uniroma3.sii.javamrs.model.RecommendationEntity;
import it.uniroma3.sii.javamrs.model.TrackEntity;
import it.uniroma3.sii.javamrs.model.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless(name="userFacade")
public class UserFacade {
	
	@PersistenceContext(unitName="my-app")
	private EntityManager em;
	
	public void test() {
		UserEntity mario = insertUser("mario_rossi@rossi.it", "Mario Rossi");
		UserEntity paolo = insertUser("paolo_bo@boh.it", "Paolo");
		UserEntity tizio = insertUser("tizio_tizietti@boh.it", "Tizio");
		UserEntity caio = insertUser("caio_caietti@boh.it", "Caio");
		UserEntity sempronio = insertUser("sempronio_sempronietti@boh.it", "Sempronio");
		UserEntity augusto = insertUser("augusto_augustini@boh.it", "Augusto");
		
		insertBig5Personality(mario, new Big5Entity(2.3, 3, 2.66, 2.51, 1.33));
		insertBig5Personality(paolo, new Big5Entity(2.8, 2.944, 2.744, 3.1, 2.5));
		insertBig5Personality(tizio, new Big5Entity(3.5, 2.533, 2.601, 2.805, 2.744));
		insertBig5Personality(caio, new Big5Entity(2.5, 2.619, 2.755, 2.601, 2.801));
		insertBig5Personality(sempronio, new Big5Entity(3.1, 2.81, 3, 2.813, 3));
		insertBig5Personality(augusto, new Big5Entity(2.1, 2.522, 2.633, 3, 4));
		
		insertRecommendation(mario, new RecommendationEntity(Arrays.asList(
				new TrackEntity("Schifetto", "Bello schifo"),
				new TrackEntity("Bellano", "Questa è bella"),
				new TrackEntity("Band metal", "Solo casino"))));
		
		insertRecommendation(paolo, new RecommendationEntity(Arrays.asList(
				new TrackEntity("Tunz tunz", "Tunz tunz"),
				new TrackEntity("Mozart", "Amadeus"))));
		
		insertRecommendation(tizio, new RecommendationEntity(Arrays.asList(
				new TrackEntity("Umberto Tozzi", "Ti amo"),
				new TrackEntity("Mina", "Le mille bolle blu"))));
		
		insertRecommendation(caio, new RecommendationEntity(Arrays.asList(
				new TrackEntity("Sanremo", "Canzone di Sanremo"),
				new TrackEntity("Nek", "Fatti avanti amore"))));
		
		insertRecommendation(sempronio, new RecommendationEntity(Arrays.asList(
				new TrackEntity("Vasco Rossi", "Manifesto futurista della nuova umanità"),
				new TrackEntity("Jovanotti", "Bella"),
				new TrackEntity("Pooh", "Buona fortuna"))));
		
		insertRecommendation(augusto, new RecommendationEntity(Arrays.asList(
				new TrackEntity("Classico", "Musica classica"),
				new TrackEntity("Franco Battiato", "La cura"))));
	}
	
	public UserEntity insertUser(String name, String email) {
		UserEntity user = new UserEntity(name, email);
		em.persist(user);
		return user;
	}
	
	public List<UserEntity> retrieveUserByEmail(String email) {
		TypedQuery<UserEntity> query = em.createNamedQuery("user.getByEmail", UserEntity.class);
		query.setParameter("p_email", email);
		return query.getResultList();
	}
	
	public List<UserEntity> retrieveUserByUid(String uid) {
		TypedQuery<UserEntity> query = em.createNamedQuery("user.getByUid", UserEntity.class);
		query.setParameter("p_uid", uid);
		return query.getResultList();
	}
	
	public List<UserEntity> retrieveAllUsers() {
		TypedQuery<UserEntity> query = em.createNamedQuery("user.getAll", UserEntity.class);
		return query.getResultList();
	}
	
	public UserEntity addQuestionary(UserEntity user, String sex, Integer age, String nationality, String work, String education,
                                     List<QuestionaryValueEntity> values) {
		if (user.hasQuestionnaire()) {
			this.removeQuestionary(user);
		}
		QuestionaryEntity questionary = new QuestionaryEntity(sex, age, nationality, work, education, values);
		em.persist(questionary);
		user.setQuestionaryEntity(questionary);
		em.merge(user);
		return user;
	}
	
	public UserEntity removeQuestionary(UserEntity user) {
		String toRemoveId = user.getQuestionaryEntity().getId();
		QuestionaryEntity toRemove = em.find(QuestionaryEntity.class, toRemoveId);
		em.remove(toRemove);
		user.setQuestionaryEntity(null);
		em.merge(user);
		return user;
	}

	public UserEntity addBig5(UserEntity user, double ape, double grad, double cosc, double nevr, double estr) {
		Big5Entity big5 = new Big5Entity(ape, grad, cosc, nevr, estr);
		em.persist(big5);
		user.setBig5Entity(big5);
		em.merge(user);
		return user;
	}
	
	public UserEntity addRecommendation(UserEntity user, List<TrackEntity> recTracks) {
		RecommendationEntity rec = new RecommendationEntity(recTracks);
		em.persist(rec);
		if (user.hasRecommendation()) {
			this.removeRecommendation(user);
		}
		user.setRecommendationEntity(rec);
		em.merge(user);
		return user;
	}
	
	public UserEntity removeRecommendation(UserEntity user) {
		String toRemoveId = user.getRecommendationEntity().getId();
		RecommendationEntity toRemove = em.find(RecommendationEntity.class, toRemoveId);
		em.remove(toRemove);
		user.setRecommendationEntity(null);
		em.merge(user);
		return user;
	}
		
	public UserEntity addEvaluation(UserEntity user, String feedback, String recEvaluated,
			                        Integer[] qValues) {
		EvaluationEntity eval = new EvaluationEntity(feedback, recEvaluated, qValues);
		em.persist(eval);
		user.addEvaluationEntity(eval);
		em.merge(user);
		return user;
	}
	
	public UserEntity insertQuestionnaire(UserEntity user, QuestionaryEntity questionary) {
		em.persist(questionary);
		user.setQuestionaryEntity(questionary);
		em.merge(user);
		return user;
	}
	
	public UserEntity insertBig5Personality(UserEntity user, Big5Entity big5) {
		em.persist(big5);
		user.setBig5Entity(big5);
		em.merge(user);
		return user;
	}
	
	public RecommendationEntity insertRecommendation(UserEntity user, List<TrackEntity> recTracks) {
		RecommendationEntity rec = new RecommendationEntity(recTracks);
		
		em.persist(rec);
		user.setRecommendationEntity(rec);
		em.merge(user);
		return rec;
	}
	
	public UserEntity insertRecommendation(UserEntity user, RecommendationEntity recommendation) {
		em.persist(recommendation);
		user.setRecommendationEntity(recommendation);
		em.merge(user);
		return user;
	}
	
	public void initializeLastFMData(String big5Csv, String preferencesCsv) {
		Map<String, UserEntity> uid2User = this.prepareUsers(big5Csv);
		Map<String, RecommendationEntity> uid2Rec = this.prepareRecs(preferencesCsv);
		
		for (String uid : uid2Rec.keySet()) {
			RecommendationEntity rec = uid2Rec.get(uid);
			UserEntity user = uid2User.get(uid);
			user.setRecommendationEntity(rec);
			em.persist(user);
		}
	}
	
	private Map<String, RecommendationEntity> prepareRecs(String preferencesCsv) {
		final Integer USER_ID_FIELD = 0;
		final Integer TRACK_FIELD = 1;
		final Integer ARTIST_FIELD = 2;
		
		Map<String, RecommendationEntity> lastFMRecs = new TreeMap<>();
		
		BufferedReader prefReader = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream(preferencesCsv))
        );
		
		String line = null;
		try {
			while ((line = prefReader.readLine()) != null) {
				String[] fields = line.split(",");
				String uid = fields[USER_ID_FIELD];
				
				String title = fields[TRACK_FIELD];
				String artist = fields[ARTIST_FIELD];
				
				if (lastFMRecs.containsKey(uid)) {
					lastFMRecs.get(uid).addTrack(new TrackEntity(artist, title));
				}
				else {
					List<TrackEntity> newList = new ArrayList<>();
					newList.add(new TrackEntity(artist, title));
					lastFMRecs.put(uid, new RecommendationEntity(newList));
				}
			}
			prefReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Errore interpretazione CSV");
		}
		return lastFMRecs;
	}
	
	private Map<String, UserEntity> prepareUsers(String big5Csv) {
		final Integer USER_ID_FIELD = 0;
		final Integer OPE_FIELD = 1;
		final Integer CON_FIELD = 2;
		final Integer EXT_FIELD = 3;
		final Integer AGR_FIELD = 4;
		final Integer NEU_FIELD = 5;
		
		Map<String, UserEntity> lastFMUsers = new TreeMap<>();
		
		BufferedReader big5Reader = new BufferedReader(
				                           new InputStreamReader(this.getClass().getResourceAsStream(big5Csv))
				                    );
		
		String line = null;
		try {
			while ((line = big5Reader.readLine()) != null) {
				String[] fields = line.split(",");
				
				UserEntity ue = new UserEntity();
				String uid = fields[USER_ID_FIELD];
				
				ue.setUid(fields[USER_ID_FIELD]);
				
				double ope = Double.parseDouble(fields[OPE_FIELD]);
				double agr = Double.parseDouble(fields[AGR_FIELD]);
				double con = Double.parseDouble(fields[CON_FIELD]);
				double neu = Double.parseDouble(fields[NEU_FIELD]);
				double ext = Double.parseDouble(fields[EXT_FIELD]);
				ue.setBig5Entity(new Big5Entity(ope, agr, con, neu, ext));
				lastFMUsers.put(uid, ue);
			}
			big5Reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Errore interpretazione CSV");
		}
		return lastFMUsers;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
