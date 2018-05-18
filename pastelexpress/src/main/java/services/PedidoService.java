package services;

import java.io.Serializable;
import java.util.List;

import dao.PedidoDAO;
import entities.Pedido;
import exception.PersistenciaDacException;
import filter.PedidoFilter;


public class PedidoService implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4114408751146726338L;
	private PedidoDAO userDAO = new PedidoDAO();
	
	public void save(Pedido pedido) throws ServiceDacException {
		try {
			// Verificar se login já existe
			userDAO.save(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public void update(Pedido pedido, boolean passwordChanged) throws ServiceDacException {
		
		try {
			userDAO.update(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public void delete(Pedido pedido) throws ServiceDacException {
		try {
			userDAO.delete(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Pedido getByID(int pedidoId) throws ServiceDacException {
		try {
			return userDAO.getByID(pedidoId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Pedido> getAll() throws ServiceDacException {
		try {
			return userDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Pedido> findBy(PedidoFilter filter) throws ServiceDacException {
		try {
			return userDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
}
