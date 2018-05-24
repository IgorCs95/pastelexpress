package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAO {

	static EntityManagerFactory emf;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("pastelexpress");
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}

	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public static void close() {
		emf.close();
	}

	protected boolean notEmpty(Object obj) {
		return obj != null;
	}

	protected boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}

}
