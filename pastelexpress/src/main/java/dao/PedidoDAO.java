package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.Pedido;
import exception.PersistenciaDacException;
import filter.PedidoFilter;


public class PedidoDAO extends DAO{
	

	public void save(Pedido obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o pedido.", pe);
		} finally {
			em.close();
		}
	}

	public Pedido update(Pedido obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Pedido resultado = obj;
		try {
			resultado = em.merge(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o pedido.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Pedido obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			obj = em.find(Pedido.class, obj.getId());
			em.remove(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o pedido.", pe);
		} finally {
			em.close();
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
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Pedido> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

	public List<Pedido> findBy(PedidoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Pedido> resultado = new ArrayList<>();

		try {
			
			String jpql = "SELECT u FROM Pedido u WHERE 1 = 1 ";
			
			if (notEmpty(filter.getCodigo())) {
				jpql += "AND u.codigo LIKE :codigo ";
			}

			if (notEmpty(filter.getDataPedidoInicio())) {
				jpql += "AND u.data >= :dataInicio ";
			}

			if (notEmpty(filter.getDataPedidoFim())) {
				jpql += "AND u.data <= :dataFim ";
			}

			TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
			
			if (notEmpty(filter.getCodigo())) {
				query.setParameter("codigo", "%" + filter.getCodigo() + "%");
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
		} finally {
			em.close();
		}
		return resultado;

	}

	

}
