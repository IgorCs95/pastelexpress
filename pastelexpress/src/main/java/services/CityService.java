package services;

import java.io.Serializable;
import java.util.List;

import dao.CityDAO;
import entities.City;
import entities.State;
import exception.PersistenciaDacException;


public class CityService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private CityDAO cityDAO = new CityDAO();
	
	public void save(City user) throws ServiceDacException {
		try {
			cityDAO.save(user);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public void update(City user) throws ServiceDacException {
		
		try {
			cityDAO.update(user);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public void delete(City user) throws ServiceDacException {
		try {
			cityDAO.delete(user);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public City getByID(int userId) throws ServiceDacException {
		try {
			return cityDAO.getByID(userId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<City> getAll() throws ServiceDacException {
		try {
			return cityDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<City> findBy(State state) throws ServiceDacException {
		try {
			return cityDAO.findBy(state);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<City> findByName(String name) throws ServiceDacException {
		try {
			return cityDAO.findByName(name);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
	
}
