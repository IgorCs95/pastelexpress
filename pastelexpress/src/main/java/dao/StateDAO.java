package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.State;
import exception.PersistenciaDacException;


public class StateDAO extends DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4407352919600342748L;

	public void save(State obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o estado.", pe);
		}
	}

	public State update(State obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		State resultado = obj;
		try {
			resultado = em.merge(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o estado.", pe);
		}
		return resultado;
	}

	public void delete(State obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(State.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o estado.", pe);
		}
	}

	public State getByID(int objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		State resultado = null;
		try {
			resultado = em.find(State.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o estado com base no ID.", pe);
		}

		return resultado;
	}

	public List<State> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<State> resultado = null;
		try {
			TypedQuery<State> query = em.createQuery("SELECT s FROM State s", State.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todos os estados.", pe);
		}
		return resultado;
	}
}
