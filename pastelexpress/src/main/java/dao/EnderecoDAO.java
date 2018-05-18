package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import entities.Endereco;
import exception.PersistenciaDacException;
import filter.EnderecoFilter;

public class EnderecoDAO extends DAO<Endereco> {


	

	private static Map<Integer, Endereco> REPOSITORY = new ConcurrentHashMap<Integer, Endereco>(
			new HashMap<Integer, Endereco>());

	private static AtomicInteger ID = new AtomicInteger();

	public void save(Endereco user) throws PersistenciaDacException {
		user.setId(ID.getAndIncrement());
		REPOSITORY.put(user.getId(), user);
	}

	public Endereco update(Endereco user) throws PersistenciaDacException {
		REPOSITORY.put(user.getId(), user);
		return null;
	}

	public void delete(Endereco user) throws PersistenciaDacException {
		REPOSITORY.remove(user.getId());
	}

	public Endereco getByID(int userId) throws PersistenciaDacException {
		Endereco user = REPOSITORY.get(userId);
		return user != null ? user.clone() : null;
	}

	public List<Endereco> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

	public List<Endereco> findBy(EnderecoFilter filter) throws PersistenciaDacException {


		return null;

	}

}
