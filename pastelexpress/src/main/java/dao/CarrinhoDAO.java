package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.Carrinho;
import exception.PersistenciaDacException;
import filter.CarrinhoFilter;

public class CarrinhoDAO extends DAO<Carrinho> {

	public void save(Carrinho item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(item);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o Carrinho.", pe);
		} finally {
			em.close();
		}
	}

	public Carrinho update(Carrinho item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Carrinho resultado = item;
		try {
			resultado = em.merge(item);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o Carrinho.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Carrinho item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			item = em.find(Carrinho.class, item.getId());
			em.remove(item);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o Carrinho.", pe);
		} finally {
			em.close();
		}
	}

	public Carrinho getByID(int itemId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Carrinho resultado = null;
		try {
			resultado = em.find(Carrinho.class, itemId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o Carrinho com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Carrinho> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Carrinho> resultado = null;
		try {
			TypedQuery<Carrinho> query = em.createQuery("SELECT u FROM Carrinho u", Carrinho.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todos os Carrinho.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}
	
	
	
	public List<Carrinho> findBy(CarrinhoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Carrinho> resultado = new ArrayList<>();

		try {

			String jpql = "SELECT u FROM Carrinho u WHERE 1 = 1 ";

			// Data Inicio
			if (notEmpty(filter.getDataInicio())) {
				jpql += "AND u.data <= :dataInicio ";
			}

			// Data Fim
			if (notEmpty(filter.getDataFim())) {
				jpql += "AND u.data <= :dataFim ";
			}

			TypedQuery<Carrinho> query = em.createQuery(jpql, Carrinho.class);

			// Data Inicio
			if (notEmpty(filter.getDataInicio())) {
				query.setParameter("dataInicio", filter.getDataInicio());
			}

			// Data Fim
			if (notEmpty(filter.getDataFim())) {
				query.setParameter("dataFim", filter.getDataFim());
			}

			resultado = query.getResultList();

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os Carrinho.", pe);
		} finally {
			em.close();
		}
		return resultado;

	}

}
