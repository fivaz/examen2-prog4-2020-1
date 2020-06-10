package ch.hesge.cours634.exam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHelper {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("access_card_system");

	public static EntityManager em() {
		return emf.createEntityManager();
	}

	public static EntityManagerFactory  emf(){
		return emf;
	}

	public static void persist(Object object) {
		EntityManager em = em();
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
	}

	public static void merge(Object object) {
		EntityManager em = em();
		em.getTransaction().begin();
		em.merge (object);
		em.getTransaction().commit();
	}


	public static <T> void remove(Class<T> entityClass, Object primaryKey) {
		EntityManager em = em();
		em.getTransaction().begin();
		Object obj= em.find(entityClass, primaryKey);
		if(obj!=null) {
			em.remove(obj);
		}
		em.getTransaction().commit();
	}

}
