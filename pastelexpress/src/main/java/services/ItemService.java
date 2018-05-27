package services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dao.ItemDAO;
import entities.Item;
import exception.PersistenciaDacException;
import filter.ItemFilter;
import util.TransacionalCdi;

@ApplicationScoped
public class ItemService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4301929765413536058L;

	@Inject
	private ItemDAO itemDAO;

	@TransacionalCdi
	public void save(Item item) throws ServiceDacException {
		try {
			itemDAO.save(item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(Item item) throws ServiceDacException {

		try {
			itemDAO.update(item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(Item item) throws ServiceDacException {
		try {
			itemDAO.delete(item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Item getByID(int itemId) throws ServiceDacException {
		try {
			return itemDAO.getByID(itemId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Item> getAll() throws ServiceDacException {
		try {
			return itemDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Item> findBy(ItemFilter filter) throws ServiceDacException {
		try {
			return itemDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
