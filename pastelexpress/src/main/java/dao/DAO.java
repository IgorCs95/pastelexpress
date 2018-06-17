package dao;

import java.io.Serializable;
import java.sql.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public abstract class DAO implements Serializable {

	private static final long serialVersionUID = 3118037959505163320L;

	@Inject
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}
	
	protected boolean notEmpty(Object obj) {
		return obj != null;
	}
	
	protected boolean notEmpty(Date obj) {
		return obj != null;
	}
	
	protected boolean notEmpty(int obj) {
		return obj != 0;
	}
	
	protected boolean notEmpty(float obj) {
		return obj != 0;
	}

	protected boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}

}
