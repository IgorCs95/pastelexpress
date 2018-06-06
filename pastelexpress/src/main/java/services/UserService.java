package services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dao.UserDAO;
import entities.User;
import exception.PersistenciaDacException;
import filter.UserFilter;
import util.TransacionalCdi;

@ApplicationScoped
public class UserService implements Serializable {

	private static final long serialVersionUID = -7803325791425670859L;
	@Inject
	private UserDAO userDAO;

	@TransacionalCdi
	public void save(User user) throws ServiceDacException {
		try {
			// Verificar se login já existe
			validarLogin(user);
			calcularHashDaSenha(user);
			userDAO.save(user);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(User user, boolean passwordChanged) throws ServiceDacException {

		try {
			// Verificar se login já existe
			validarLogin(user);
			if (passwordChanged) {
				calcularHashDaSenha(user);
			}
			userDAO.update(user);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(User user) throws ServiceDacException {
		try {
			userDAO.delete(user);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public User getByID(int userId) throws ServiceDacException {
		try {
			return userDAO.getByID(userId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<User> getAll() throws ServiceDacException {
		try {
			return userDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<User> findBy(UserFilter filter) throws ServiceDacException {
		try {
			return userDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	private String calcularHashDaSenha(User user) throws ServiceDacException {
		user.setSenha(hash(user.getSenha()));
		return user.getSenha();
	}

	public boolean senhaAtualConfere(String passwordAtualHash, String confirmacaoPasswordAtual)
			throws ServiceDacException {

		if (passwordAtualHash == null && confirmacaoPasswordAtual == null) {
			return true;
		}

		if (passwordAtualHash == null || confirmacaoPasswordAtual == null) {
			return false;
		}

		String confirmacaoPasswordAtualHash = hash(confirmacaoPasswordAtual);

		return passwordAtualHash.equals(confirmacaoPasswordAtualHash);
	}

	/**
	 * Método que calcula o hash de uma dada senha usando o algoritmo SHA-256.
	 * 
	 * @param password
	 *            senha a ser calculada o hash
	 * @return hash da senha
	 * @throws ServiceDacException
	 *             lançada caso ocorra algum erro durante o processo
	 */
	private String hash(String password) throws ServiceDacException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String output = bigInt.toString(16);
			return output;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new ServiceDacException("Could not calculate hash!", e);
		}
	}

	public User isUsuarioVerificarLogin(String email, String senha) throws PersistenciaDacException {
		try {
			email = email.toLowerCase().trim();
			User retorno = userDAO.getUser(email.toLowerCase().trim());

			if (retorno.getSenha().equals(hash(senha))) {
				return retorno;
			} else {
				throw new ServiceDacException("Usuario Nao encontrado");
			}

		} catch (ServiceDacException | PersistenciaDacException e) {
			throw new PersistenciaDacException(e.getMessage());
		}
	}

	public boolean validarLogin(User user) throws ServiceDacException {
		boolean jahExiste = userDAO.existeUsuarioComLogin(user);
		if (jahExiste) {
			throw new ServiceDacException("Login already exists: " + user.getLogin());
		}
		return jahExiste;
	}

}
