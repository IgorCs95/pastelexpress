package services;

import java.io.Serializable;
import java.util.List;

import dao.ItemDAO;
import entities.Item;
import exception.PersistenciaDacException;
import filter.ItemFilter;


public class ItemService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4301929765413536058L;
	private ItemDAO itemDAO = new ItemDAO();
	
	public void save(Item item) throws ServiceDacException {
		try {
			itemDAO.save(item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public void update(Item item) throws ServiceDacException {
		
		try {
			itemDAO.update(item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

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
