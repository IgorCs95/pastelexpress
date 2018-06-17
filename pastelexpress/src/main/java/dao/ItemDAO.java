package dao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.el.EvaluationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.Item;
import exception.PersistenciaDacException;
import filter.ItemFilter;

public class ItemDAO extends DAO {
	
	private static final long serialVersionUID = -2373193930046892744L;

	public void save(Item item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Problemas ao cadastrar o Item ", pe);
		}
	}

	public Item update(Item item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Item resultado = item;
		try {
			resultado = em.merge(item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Problema na atualização do Item ", pe);
		}
		return resultado;
	}

	public void delete(Item item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			item = em.find(Item.class, item.getId());
			em.remove(item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("erro ao remover o Item", pe);
		}
	}

	public Item getByID(int itemId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Item resultado = null;
		try {
			resultado = em.find(Item.class, itemId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Nao foi possivel encontrar o item pelo id fornecido", pe);
		}

		return resultado;
	}

	public List<Item> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Item> resultado = null;
		try {
			TypedQuery<Item> query = em.createQuery("SELECT u FROM Item u ORDER BY id ASC", Item.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todos os Item.", pe);
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
				jpql += "AND LOWER(u.nome) LIKE :nome ";
			}

			// Menor Preco
			if (filter.isOrdenarMenorPreco()) {
				jpql += "ORDER BY valor ASC ";
			}
			
			// Maior Preco
			if (filter.isOrdenarMaiorPreco()) {
				jpql += "ORDER BY valor DESC ";
			}


			TypedQuery<Item> query = em.createQuery(jpql, Item.class);

			// Nome
			if (notEmpty(filter.getNome())) {
				query.setParameter("nome", "%" + filter.getNome() + "%");
			}



			resultado = query.getResultList();

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os Item.", pe);
		}
		return resultado;

	}


}
