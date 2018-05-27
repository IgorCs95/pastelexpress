package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.City;
import entities.State;
import exception.PersistenciaDacException;


public class CityDAO extends DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8125884107212358354L;

	public void save(City obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a cidade.", pe);
		}
	}

	public City update(City obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		City resultado = obj;
		try {
			resultado = em.merge(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a cidade.", pe);
		}
		return resultado;
	}

	public void delete(City obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(City.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a cidade.", pe);
		}
	}

	public City getByID(int objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		City resultado = null;
		try {
			resultado = em.find(City.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a cidade com base no ID.", pe);
		}

		return resultado;
	}

	public List<City> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<City> resultado = null;
		try {
			TypedQuery<City> query = em.createQuery("SELECT c FROM City c", City.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todas as cidades.", pe);
		}
		return resultado;
	}

	public List<City> findBy(State state) throws PersistenciaDacException {
		if (state == null) {
			return getAll(); 
		}

		EntityManager em = getEntityManager();

		String jpql = "SELECT c FROM City c WHERE c.state = :state";

		TypedQuery<City> query = em.createQuery(jpql, City.class);

		query.setParameter("state", state);
		
		return query.getResultList();
	}

	public List<City> findByName(String name) throws PersistenciaDacException {
		if (name == null) {
			return getAll(); 
		}

		EntityManager em = getEntityManager();

		String jpql = "SELECT c FROM City c WHERE c.name = :name";

		TypedQuery<City> query = em.createQuery(jpql, City.class);

		query.setParameter("name", name);
		
		return query.getResultList();
	}
}
