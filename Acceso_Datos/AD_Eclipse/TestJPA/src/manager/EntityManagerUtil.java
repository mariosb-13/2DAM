package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class EntityManagerUtil implements AutoCloseable {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestJPA");
	private static EntityManager em = emf.createEntityManager();

	public static EntityManager getEntityManager() {
		return em;
	}

	public static void release() {
		em.close();
		emf.close();
	}
	
	@Override
	public void close() throws Exception {
		release();
	}

	/**
	 * Almacenara en la BD la Entidad
	 * 
	 * @param object Entidad que sera almacenada en la BD
	 */
	public static boolean persist(Object object) {
		em.getTransaction().begin();
		try {
			em.persist(object);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Error al peristir:" + e.getMessage());
			em.getTransaction().rollback();
			return false;
		}
	}

	/**
	 * Eliminara de la BD la Entidad
	 * 
	 * @param object Entidad que sera eliminada de la BD
	 */
	public static boolean delete(Object object) {
		em.getTransaction().begin();
		try {
			em.remove(em.merge(object));// Necesita permiso L/E
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Error al borrar:" + e.getMessage());
			em.getTransaction().rollback();
			return false;
		}
	}

// FUNCIONES ADICIONALES
// TODO
}