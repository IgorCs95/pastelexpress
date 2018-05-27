package dao;

import java.util.ArrayList;
import java.util.List;

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
			TypedQuery<Item> query = em.createQuery("SELECT u FROM Item u", Item.class);
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

	public boolean existeCodigo(Item item) {

		if (item == null || !notEmpty(item.getCodigo())) {
			return false;
		}

		EntityManager em = getEntityManager();

		String jpql = "SELECT COUNT(*) FROM Item u WHERE u.cadigo = :cadigo ";
		if (item.getId() != null) {
			jpql += "AND u != :item ";
		}

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		query.setParameter("cadigo", item.getCodigo());

		if (item.getId() != null) {
			query.setParameter("item", item);
		}

		Long count = query.getSingleResult();
		return count > 0;
	}

}
