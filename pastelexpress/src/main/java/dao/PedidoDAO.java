package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import entities.Pedido;
import exception.PersistenciaDacException;
import filter.PedidoFilter;


public class PedidoDAO extends DAO<Pedido>{
	

	private static Map<Integer, Pedido> REPOSITORY = new ConcurrentHashMap<Integer, Pedido>(new HashMap<Integer, Pedido>());

	private static AtomicInteger ID = new AtomicInteger();

	public void save(Pedido user) throws PersistenciaDacException {
		user.setId(ID.getAndIncrement());
		REPOSITORY.put(user.getId(), user);
	}

	public Pedido update(Pedido user) throws PersistenciaDacException {
		REPOSITORY.put(user.getId(), user);
		return null;
	}

	public void delete(Pedido user) throws PersistenciaDacException {
		REPOSITORY.remove(user.getId());
	}

	public Pedido getByID(int userId) throws PersistenciaDacException {
		Pedido user = REPOSITORY.get(userId);
		return user != null ? user.clone() : null;
	}

	public List<Pedido> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

	public List<Pedido> findBy(PedidoFilter filter) throws PersistenciaDacException {

	

		return null;

	}

	

}
