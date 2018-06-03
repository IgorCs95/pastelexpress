package services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dao.ItemPedidoDAO;
import entities.ItemPedido;
import exception.PersistenciaDacException;
import filter.ItemPedidoFilter;

@ApplicationScoped
public class ItemPedidoService implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2018075416903134371L;
	@Inject
	private ItemPedidoDAO ipDAO;


	public ItemPedido getByID(int itemId) throws ServiceDacException {
		try {
			return ipDAO.getByID(itemId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<ItemPedido> findBy(ItemPedidoFilter filter) throws ServiceDacException {
		try {
			return ipDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
