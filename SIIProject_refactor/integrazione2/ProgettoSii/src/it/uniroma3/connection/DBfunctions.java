package it.uniroma3.connection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Objects.*;


public class DBfunctions {
	public void addUser(String name, String email, String ID, String UID){
		UserEntity user= null;
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
  		EntityManager em = emf.createEntityManager();
  		EntityTransaction tx = em.getTransaction();
  		tx.begin();
  		user=em.find(UserEntity.class,ID);
  		tx.commit();
  		if(user==null){
  			user=new UserEntity();
  			user.setName(name);
  			user.setID(ID);
  			user.setEmail(email);
  			user.setUID(UID);
  			tx.begin();
  			em.persist(user);
  			tx.commit();
  			}
  		else{
  			updateUser(ID,name,email,UID);	
  		}
  		em.close();
		emf.close(); 
      
	}
	
	//Trova utente da id
	public UserEntity checkuserbyID(String id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
		EntityManager em = emf.createEntityManager();
		UserEntity user = new UserEntity();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		user =em.find(UserEntity.class,id);
		tx.commit();
		em.close();
		emf.close();
		return user;
	}
	//Trova tutti gli utenti con nome inserito
	public List<UserEntity> checkuserbyName(String name){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<UserEntity> userlist= new ArrayList<UserEntity>();
		tx.begin();
		TypedQuery<UserEntity> q2 =
			      em.createQuery("SELECT c FROM UserEntity c WHERE c.Name=:name", UserEntity.class);
		userlist=q2.setParameter("name", name).getResultList();
		tx.commit();
		em.close();
		emf.close();
		return userlist;
	}
	//Trova tutti gli utenti con email inserita
	public List<UserEntity> checkuserbyEmail(String email){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<UserEntity> userlist= new ArrayList<UserEntity>();
		tx.begin();
		TypedQuery<UserEntity> q2 =
			      em.createQuery("SELECT c FROM UserEntity c WHERE c.Email=:email", UserEntity.class);
		userlist=q2.setParameter("email", email).getResultList();
		tx.commit();
		em.close();
		emf.close();
		return userlist;
	}
	//Trova tutti gli utenti con UID inserito
	public List<UserEntity> checkuserbyUID(String uid){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<UserEntity> userlist= new ArrayList<UserEntity>();
		tx.begin();
		TypedQuery<UserEntity> q2 =
			      em.createQuery("SELECT c FROM UserEntity c WHERE c.UID=:uid", UserEntity.class);
		userlist=q2.setParameter("uid", uid).getResultList();
		tx.commit();
		em.close();
		emf.close();
		return userlist;
	}
	//Update che cerca id e aggiorna nome, email, e UID. Se uno di questi è null lascia precedente
	public void updateUser(String id, String newname, String newemail, String newuid){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
		EntityManager em = emf.createEntityManager();
		UserEntity user= em.find(UserEntity.class,id);
		if(newname!=null){
			em.getTransaction().begin();
			user.setName(newname);
			em.getTransaction().commit();
		}
		if(newemail!=null){
			em.getTransaction().begin();
			user.setEmail(newemail);
			em.getTransaction().commit();
		}
			
		if(newuid!=null){
			em.getTransaction().begin();
			user.setUID(newuid);
			em.getTransaction().commit();
		}	
		em.close();
		emf.close();
		}
	//Lista di id
	public List<String> findID(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<String> idlist= new ArrayList<String>();
		tx.begin();
		TypedQuery<String> q2 =
			      em.createQuery("SELECT c.ID FROM UserEntity c ", String.class);
		idlist=q2.getResultList();
		tx.commit();
		em.close();
		emf.close();
		return idlist;
	}
	
		//Aggiunta personalità nel DB
      public void addPersonality(String Id, double ape, double grad, double cosc, double nevr, double estr){
    	
    	Big5Entity big5= null;
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
  		EntityManager em = emf.createEntityManager();
  		EntityTransaction tx = em.getTransaction();
  		tx.begin();
  		big5=em.find(Big5Entity.class,Id);
  		tx.commit();
  		if(big5==null){
  			Big5Entity big= new Big5Entity(Id,ape,grad,cosc,nevr,estr);
  			tx.begin();
  			em.persist(big);
  			tx.commit();
  			}
  		else{
  			em.getTransaction().begin();
  			big5.setApe(ape);
  			big5.setGrad(grad);
  			big5.setCosc(cosc);
  			big5.setNevr(nevr);
  			big5.setEstr(estr);
  			em.getTransaction().commit();	
  		}
  		em.close();
		emf.close(); 
      }
      //Trova personalità dall'utente
      public Big5Entity getPersonality(String Id){
    	  Big5Entity big= new Big5Entity();
    	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
    		EntityManager em = emf.createEntityManager();
    		EntityTransaction tx = em.getTransaction();
    		tx.begin();
    		big=em.find(Big5Entity.class,Id);
    		tx.commit();
    		em.close();
    		emf.close();
    	  return big;
      }
      
      public List<Double> getBig5(String Id){
    	  Big5Entity big= getPersonality(Id);
    	  List<Double> lista=new ArrayList<Double>();
    	  lista.add(big.getApe());
    	  lista.add(big.getGrad());
    	  lista.add(big.getCosc());
    	  lista.add(big.getNevr());
    	  lista.add(big.getEstr());
    	  return lista;
    	  
      }
      
      //inserisco nel DB risposte al questionario
      public void addQuestionary(String ID, String Sex, int Age, String Nationality, String Work, 
  			String Education, int q1, int q2, int q3, int q4, int q5, int q6, int q7, int q8, 
  			int q9, int q10, int q11, int q12, int q13, int q14, int q15, int q16, int q17, 
  			int q18, int q19, int q20, int q21, int q22, int q23, int q24, int q25, int q26,
  			int q27, int q28, int q29, int q30, int q31, int q32, int q33, int q34, int q35,
  			int q36, int q37, int q38, int q39, int q40, int q41, int q42,int q43, int q44){
    	    QuestionaryEntity q=new QuestionaryEntity(ID,Sex,Age,Nationality,Work,Education,q1,
    			  								q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,
    			  								q16,q17,q18,q19,q20,q21,q22,q23,q24,q25,q26,q27,
    			  								q28,q29,q30,q31,q32,q33,q34,q35,q36,q37,q38,q39,
    			  								q40,q41,q42,q43,q44);
    	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
      		EntityManager em = emf.createEntityManager();
      		EntityTransaction tx = em.getTransaction();
      		tx.begin();
      		em.persist(q);
      		tx.commit();
      		em.close();
    		emf.close();
      }
      //Prende le risposte al questionario dell'utente
      public QuestionaryEntity getQuestionary(String ID){
        QuestionaryEntity q=new QuestionaryEntity();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
  		EntityManager em = emf.createEntityManager();
  		EntityTransaction tx = em.getTransaction();
  		tx.begin();
  		q =em.find(QuestionaryEntity.class,ID);
  		tx.commit();
  		em.close();
  		emf.close();
  		return q;
      }
      //Se l'utente non ha fatto il test ritorna vero, altrimenti falso
      public boolean checkQuestIsEmpty(String ID){
    	  QuestionaryEntity q=getQuestionary(ID);
    	  if(q==null)
    		  return true;
    	  else
    		  return false;
      }
      //restituisce l'utente e le sue tracce consigliate
      public RecommendedSongsQUEST getTrackUser(String ID){
    	  RecommendedSongsQUEST brani= new RecommendedSongsQUEST();
    	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
       	  EntityManager em = emf.createEntityManager();
    	  EntityTransaction tx = em.getTransaction();
    	  tx.begin();
    	  brani =em.find(RecommendedSongsQUEST.class,ID);
    	  tx.commit();
    	  em.close();
    	  emf.close();
    	  return brani;
      }
      
      public boolean hasUserSongs(String ID){
    	  RecommendedSongsQUEST brani= null;
    	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
       	  EntityManager em = emf.createEntityManager();
    	  EntityTransaction tx = em.getTransaction();
    	  tx.begin();
    	  brani =em.find(RecommendedSongsQUEST.class,ID);
    	  tx.commit();
    	  em.close();
    	  emf.close();
    	  if(brani==null){
    		  return false;
    	  }
    	  else
    		  return true;
      }
      
      public List<TrackEntity> takeTracks(String ID){
    	  List<TrackEntity> lista=new ArrayList<TrackEntity>();
    	  List<String> id= getTrackUser(ID).getTrackID();
    	  for(String canzone: id){
    		  lista.add(getTrack(canzone));
    	  }
    	  return lista;
      }
      
      public void insertRecTracks(String ID, List<String> Tracks){
    	  RecommendedSongsQUEST brani= new RecommendedSongsQUEST(ID,Tracks);
    	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
       	  EntityManager em = emf.createEntityManager();
    	  EntityTransaction tx = em.getTransaction();
    	  tx.begin();
    	  em.persist(brani);
    	  tx.commit();
    	  em.close();
    	  emf.close();
      }
      //Inserisco tra i consigliati di un utente una traccia
	public void insertTrack(String ID, String Track){
    	  RecommendedSongsQUEST brani= null;
    	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
    	  EntityManager em = emf.createEntityManager();
    	  EntityTransaction tx = em.getTransaction();
    	  tx.begin();
    	  brani=em.find(RecommendedSongsQUEST.class,ID);
    	  tx.commit();
    	  if(brani==null){
    		  brani=new RecommendedSongsQUEST(ID);
    		  brani.setTrackID(Track);
    		  tx.begin();
        	  em.persist(brani);
        	  tx.commit();
    	  }
    	  else{
    		  em.getTransaction().begin();
    		  brani.setTrackID(Track);
    		  em.getTransaction().commit();}
    	  em.close();
    	  emf.close();
    	  
      }
	//ritorna true se esistono canzoni per l'utente
	public boolean ExistRaccomandation(String ID){
		boolean notrecc;
		RecommendedSongsQUEST song=null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
  	    EntityManager em = emf.createEntityManager();
  	    EntityTransaction tx = em.getTransaction();
  	    tx.begin();
  	    song=em.find(RecommendedSongsQUEST.class,ID);
  	    tx.commit();
		if(song!=null)
			notrecc=true;
		else
			notrecc=false;
		em.close();
	    emf.close();
		return notrecc;
	}
	//Prende brano dalla libreria dei brani
	public TrackEntity getTrack(String ID){
		TrackEntity brano= new TrackEntity();
  	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
     	EntityManager em = emf.createEntityManager();
     	EntityTransaction tx = em.getTransaction();
     	tx.begin();
     	brano =em.find(TrackEntity.class,ID);
     	tx.commit();
     	em.close();
     	emf.close();
     	return brano;
	}
	//Se l'id non è presente nella libreria aggiungo la canzone, altrimenti la aggiorno
	public void addTrack(String Title, String Artist){
		TrackEntity brano=null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-app");
     	EntityManager em = emf.createEntityManager();
     	EntityTransaction tx = em.getTransaction();
     	tx.begin();
     	brano=em.find(TrackEntity.class,Title.concat(Artist));
     	tx.commit();
     	if(brano==null){
     		brano= new TrackEntity(Title.concat(Artist),Title,Artist);
     		tx.begin();
     		em.persist(brano);
     		tx.commit();}
     	else{
     		em.getTransaction().begin();
     		brano.setTitle(Title);
     		brano.setArtist(Artist);
     		em.getTransaction().commit();
     	}
     	em.close();
     	emf.close();
	}
}
