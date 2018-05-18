package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.City;
import entities.State;
import exception.PersistenciaDacException;


public class CityDAO extends DAO<City> {

	public void save(City obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a cidade.", pe);
		} finally {
			em.close();
		}
	}

	public City update(City obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		City resultado = obj;
		try {
			resultado = em.merge(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a cidade.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(City obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			obj = em.find(City.class, obj.getId());
			em.remove(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a cidade.", pe);
		} finally {
			em.close();
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
		} finally {
			em.close();
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
		} finally {
			em.close();
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
