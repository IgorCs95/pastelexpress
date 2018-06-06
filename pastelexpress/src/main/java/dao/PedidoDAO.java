package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.Pedido;
import exception.PersistenciaDacException;
import filter.PedidoFilter;

public class PedidoDAO extends DAO {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6352858581267508566L;

	public void save(Pedido obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(obj);			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Problemas ao cadastrar o Pedido ", pe);
		}
	}

	public Pedido update(Pedido obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Pedido resultado = obj;
		try {
			resultado = em.merge(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o pedido.", pe);
		}
		return resultado;
	}

	public void delete(Pedido obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Pedido.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o pedido.", pe);
		}
	}

	public Pedido getByID(int objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Pedido resultado = null;
		try {
			resultado = em.find(Pedido.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o pedido com base no ID.", pe);
		}

		return resultado;
	}

	public List<Pedido> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Pedido> resultado = null;
		try {
			TypedQuery<Pedido> query = em.createQuery("SELECT u FROM Pedido u", Pedido.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Nao foi possivel buscar todos os Pedido.", pe);
		}
		return resultado;
	}

	public List<Pedido> findBy(PedidoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Pedido> resultado = new ArrayList<>();

		try {

			String jpql = "SELECT u FROM Pedido u WHERE 1 = 1 ";
			
			if (notEmpty(filter.getIdUser())) {
				jpql += "AND u.user = :idUser ";
			}

			if (notEmpty(filter.getDataPedidoInicio())) {
				jpql += "AND u.data >= :dataInicio ";
			}

			if (notEmpty(filter.getDataPedidoFim())) {
				jpql += "AND u.data <= :dataFim ";
			}

			TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
			
			if (notEmpty(filter.getIdUser())) {
				query.setParameter("idUser",filter.getIdUser());
			}
			

			if (notEmpty(filter.getDataPedidoInicio())) {
				query.setParameter("dataInicio", filter.getDataPedidoInicio());
			}

			// Birthday end
			if (notEmpty(filter.getDataPedidoFim())) {
				query.setParameter("dataFim", filter.getDataPedidoFim());
			}

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os pedidos.", pe);
		} 
		return resultado;

	}

}
