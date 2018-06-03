package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entities.User;
import exception.PersistenciaDacException;
import filter.UserFilter;

public class UserDAO extends DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8544940108094464776L;

	public void save(User obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o usuário.", pe);
		}
	}

	public User update(User obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		User resultado = obj;
		try {
			resultado = em.merge(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o usuário.", pe);
		}
		return resultado;
	}

	public void delete(User obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(User.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("erro ao remover o Usuario", pe);
		}
	}

	public User getByID(int objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		User resultado = null;
		try {
			resultado = em.find(User.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Nao foi possivel encontrar o Usuario pelo id fornecido", pe);
		}

		return resultado;
	}

	public User getUser(String login) throws PersistenciaDacException {

		if (!notEmpty(login)) {
			return null;
		}

		EntityManager em = getEntityManager();

		String jpql = "SELECT u FROM User u WHERE u.login = :login ";

		TypedQuery<User> query = em.createQuery(jpql, User.class);

		query.setParameter("login", login);

		List<User> user = query.getResultList();

		if (user.size() == 0) {
			throw new PersistenciaDacException("Login ou senha Invalido");
		}

		return user.get(0);

	}

	public List<User> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<User> resultado = null;
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Nao foi possivel buscar todos os usuarios.", pe);
		}
		return resultado;
	}

	public List<User> findBy(UserFilter filter) throws PersistenciaDacException {

		EntityManager em = getEntityManager();
		List<User> resultado = new ArrayList<>();

		try {

			String jpql = "SELECT u FROM User u WHERE 1 = 1 ";

			// First name
			if (notEmpty(filter.getNome())) {
				jpql += "AND u.nome LIKE :nome ";
			}

			// Birthday begin
			if (notEmpty(filter.getDataNascimentoInicio())) {
				jpql += "AND u.dataDeNascimento >= :dataNascimentoInicio ";
			}

			// Birthday end
			if (notEmpty(filter.getDataNascimentoFim())) {
				jpql += "AND u.dataDeNascimento <= :dataNascimentoFim ";
			}

			// Group
			if (notEmpty(filter.getTipo())) {
				jpql += "AND u.tipo = :tipo ";
			}

			TypedQuery<User> query = em.createQuery(jpql, User.class);

			// First name
			if (notEmpty(filter.getNome())) {
				query.setParameter("nome", "%" + filter.getNome() + "%");
			}

			// Birthday begin
			if (notEmpty(filter.getDataNascimentoInicio())) {
				query.setParameter("dataNascimentoInicio", filter.getDataNascimentoInicio());
			}

			// Birthday end
			if (notEmpty(filter.getDataNascimentoFim())) {
				query.setParameter("dataNascimentoFim", filter.getDataNascimentoFim());
			}

			// Group
			if (notEmpty(filter.getTipo())) {
				query.setParameter("tipo", filter.getTipo());
			}

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os usuários.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public boolean existeUsuarioComLogin(User user) {
		if (user == null || !notEmpty(user.getLogin())) {
			return false;
		}

		EntityManager em = getEntityManager();

		String jpql = "SELECT COUNT(*) FROM User u WHERE u.login = :login ";
		if (user.getId() != null) {
			jpql += "AND u != :user ";
		}

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		query.setParameter("login", user.getLogin());
		if (user.getId() != null) {
			query.setParameter("user", user);
		}

		Long count = query.getSingleResult();
		return count > 0;

	}

}
