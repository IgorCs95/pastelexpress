package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.Item;
import exception.PersistenciaDacException;
import filter.ItemFilter;

public class ItemDAO extends DAO<Item> {

	public void save(Item item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(item);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o Item.", pe);
		} finally {
			em.close();
		}
	}

	public Item update(Item item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Item resultado = item;
		try {
			resultado = em.merge(item);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o Item.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Item item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			item = em.find(Item.class, item.getId());
			em.remove(item);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o Item.", pe);
		} finally {
			em.close();
		}
	}

	public Item getByID(int itemId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Item resultado = null;
		try {
			resultado = em.find(Item.class, itemId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o Item com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Item> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Item> resultado = null;
		try {
			TypedQuery<Item> query = em.createQuery("SELECT u FROM Item u", Item.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todos os Item.", pe);
		} finally {
			em.close();
		}
		
		return resultado;
	}

	public List<Item> findBy(ItemFilter filter) throws PersistenciaDacException {

		EntityManager em = getEntityManager();
		List<Item> resultado = new ArrayList<>();

		try {

			String jpql = "SELECT u FROM Item u WHERE 1 = 1 ";

			// Nome
			if (notEmpty(filter.getNome())) {
				jpql += "AND u.nome LIKE :nome ";
			}

			// ValorMinimo
			if (notEmpty(filter.getValorMinimo())) {
				jpql += "AND u.valor <= :valorMinimo ";
			}

			// ValorMaximo
			if (notEmpty(filter.getValorMaximo())) {
				jpql += "AND u.valor <= :valorMaximo ";
			}

			// Codigo
			if (notEmpty(filter.getCodigo())) {
				jpql += "AND u.codigo = :codigo ";
			}

			TypedQuery<Item> query = em.createQuery(jpql, Item.class);

			// Nome
			if (notEmpty(filter.getNome())) {
				query.setParameter("nome", "%" + filter.getNome() + "%");
			}

			// Valor Minimo
			if (notEmpty(filter.getValorMinimo())) {
				query.setParameter("valorMinimo", filter.getValorMinimo());
			}

			// Valor Maximo
			if (notEmpty(filter.getValorMaximo())) {
				query.setParameter("valorMaximo", filter.getValorMaximo());
			}

			// Codigo
			if (notEmpty(filter.getCodigo())) {
				query.setParameter("codigo", filter.getCodigo());
			}

			resultado = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os Item.", pe);
		} finally {
			em.close();
		}
		return resultado;

	}

}
