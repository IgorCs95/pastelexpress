package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import exception.PersistenciaDacException;

public abstract class DAO <t>{
	
static EntityManagerFactory emf;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("pastelexpress");
		} catch(Throwable e) {
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


	public abstract void save(t obj) throws PersistenciaDacException;
	
	public abstract t update(t obj) throws PersistenciaDacException;

	public abstract void delete(t obj) throws PersistenciaDacException ;

	public abstract t getByID(int objId) throws PersistenciaDacException;

	public abstract List<t> getAll() throws PersistenciaDacException;
	
	
	protected boolean notEmpty(Object obj) {
		return obj != null;
	}
	
	protected boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}
	
	

}
