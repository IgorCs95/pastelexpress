package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.ItemPedido;
import exception.PersistenciaDacException;
import filter.ItemPedidoFilter;

public class ItemPedidoDAO extends DAO {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1181174652485671807L;

	public ItemPedido getByID(int itemId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		ItemPedido resultado = null;
		try {
			resultado = em.find(ItemPedido.class, itemId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Nao foi possivel encontrar o item pelo id fornecido", pe);
		}

		return resultado;
	}

	public List<ItemPedido> findBy(ItemPedidoFilter filter) throws PersistenciaDacException {

		EntityManager em = getEntityManager();
		List<ItemPedido> resultado = new ArrayList<>();

		try {

			String jpql = "SELECT u FROM ItemPedido u WHERE 1 = 1 ";

			// Nome
			if (notEmpty(filter.getIdPedido())) {
				jpql += "AND u.fk_pedido LIKE :idPedido ";
			}

			TypedQuery<ItemPedido> query = em.createQuery(jpql, ItemPedido.class);

			// Nome
			if (notEmpty(filter.getIdPedido())) {
				query.setParameter("idPedido", filter.getIdPedido());
			}

			resultado = query.getResultList();

		} catch (PersistenceException pe) {
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os Item.", pe);
		} finally {
			em.close();
		}
		return resultado;

	}
}