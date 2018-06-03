package services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dao.PedidoDAO;
import entities.Pedido;
import exception.PersistenciaDacException;
import filter.PedidoFilter;
import util.TransacionalCdi;

@ApplicationScoped
public class PedidoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4114408751146726338L;
	
	@Inject
	private PedidoDAO pedidoDAO;

	@TransacionalCdi
	public void save(Pedido pedido) throws ServiceDacException {
		try {
			// Verificar se login já existe
			pedidoDAO.save(pedido);
			
			
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(Pedido pedido, boolean passwordChanged) throws ServiceDacException {

		try {
			pedidoDAO.update(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(Pedido pedido) throws ServiceDacException {
		try {
			pedidoDAO.delete(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Pedido getByID(int pedidoId) throws ServiceDacException {
		try {
			return pedidoDAO.getByID(pedidoId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Pedido> getAll() throws ServiceDacException {
		try {
			return pedidoDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Pedido> findBy(PedidoFilter filter) throws ServiceDacException {
		try {
			return pedidoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
}
