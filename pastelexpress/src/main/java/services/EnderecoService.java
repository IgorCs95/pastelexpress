package services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dao.EnderecoDAO;
import entities.Endereco;
import exception.PersistenciaDacException;
import filter.EnderecoFilter;
import util.TransacionalCdi;

@ApplicationScoped
public class EnderecoService implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7765431684818258717L;
	@Inject
	private EnderecoDAO endDAO;

	@TransacionalCdi
	public void save(Endereco end) throws ServiceDacException {
		try {
			endDAO.save(end);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(Endereco end) throws ServiceDacException {

		try {
			endDAO.update(end);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(Endereco end) throws ServiceDacException {
		try {
			endDAO.delete(end);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Endereco getByID(int endId) throws ServiceDacException {
		try {
			return endDAO.getByID(endId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Endereco> getAll() throws ServiceDacException {
		try {
			return endDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Endereco> findBy(EnderecoFilter filter) throws ServiceDacException {
		try {
			return endDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}



	
	
	

}