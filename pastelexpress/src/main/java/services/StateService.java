package services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dao.StateDAO;
import entities.State;
import exception.PersistenciaDacException;
import util.TransacionalCdi;

@ApplicationScoped
public class StateService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	@Inject
	private StateDAO stateDAO;

	@TransacionalCdi
	public void save(State user) throws ServiceDacException {
		try {
			stateDAO.save(user);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(State user) throws ServiceDacException {

		try {
			stateDAO.update(user);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(State user) throws ServiceDacException {
		try {
			stateDAO.delete(user);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public State getByID(int userId) throws ServiceDacException {
		try {
			return stateDAO.getByID(userId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<State> getAll() throws ServiceDacException {
		try {
			return stateDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
