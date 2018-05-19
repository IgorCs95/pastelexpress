package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.Endereco;
import exception.PersistenciaDacException;
import filter.EnderecoFilter;

public class EnderecoDAO extends DAO<Endereco> {


	

	public void save(Endereco obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o endereco.", pe);
		} finally {
			em.close();
		}
	}

	public Endereco update(Endereco obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Endereco resultado = obj;
		try {
			resultado = em.merge(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o endereco.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Endereco obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			obj = em.find(Endereco.class, obj.getId());
			em.remove(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o endereco.", pe);
		} finally {
			em.close();
		}
	}

	public Endereco getByID(int objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Endereco resultado = null;
		try {
			resultado = em.find(Endereco.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o endereco com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Endereco> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

	public List<Endereco> findBy(EnderecoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Endereco> resultado = new ArrayList<>();

		try {
			
			String jpql = "SELECT u FROM Endereco u WHERE 1 = 1 ";
			
			if (notEmpty(filter.getRua())) {
				jpql += "AND u.rua LIKE :rua ";
			}
			
			if (notEmpty(filter.getBairro())) {
				jpql += "AND u.bairro LIKE :bairro ";
			}
			
			if (notEmpty(filter.getCep())) {
				jpql += "AND u.cep LIKE :cep ";
			}
			
			if (notEmpty(filter.getNumero())) {
				jpql += "AND u.numero LIKE :numero ";
			}

			
			TypedQuery<Endereco> query = em.createQuery(jpql, Endereco.class);
			
			if (notEmpty(filter.getRua())) {
				query.setParameter("rua", "%" + filter.getRua() + "%");
			}

			if (notEmpty(filter.getBairro())) {
				query.setParameter("bairro", "%" + filter.getBairro() + "%");
			}
			
			if (notEmpty(filter.getRua())) {
				query.setParameter("cep", "%" + filter.getCep() + "%");
			}
			
			if (notEmpty(filter.getRua())) {
				query.setParameter("numero", "%" + filter.getNumero() + "%");
			}
			
			
			
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o Endereco.", pe);
		} finally {
			em.close();
		}
		return resultado;

	}

}
