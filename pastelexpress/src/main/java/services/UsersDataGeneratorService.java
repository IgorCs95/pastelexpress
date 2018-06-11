package services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ennum.TipoUser;
import entities.City;
import entities.Endereco;
import entities.User;
import filter.UserFilter;
import util.DateUtil;

@ApplicationScoped
public class UsersDataGeneratorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3371124651147613246L;

	@Inject
	private UserService userService;

	@Inject
	private CityService cityService;

	@Inject
	private EnderecoService endService;

	public void generateData() throws ServiceDacException {
		List<User> usuarios = new ArrayList<User>();

		User admin = getUsuarioAdmin();
		User cliente = getUsuarioCliente();
		User cozinheiro = getUsuarioCozinheiro();
		User entregador = getUsuarioEntregador();

		UserFilter filter = new UserFilter();

		City city = cityService.findByName("Monteiro").get(0);

		filter.setLogin(admin.getLogin());

		List<User> users = userService.findBy(filter);
		if (users.isEmpty()) {
			admin.setEndereco(getEndereco(city));
			usuarios.add(admin);
		}

		// cliente
		filter.setLogin(cliente.getLogin());
		users = userService.findBy(filter);
		if (users.isEmpty()) {
			cliente.setEndereco(getEndereco(city));
			usuarios.add(cliente);
		}

//		// cozinheiro
//		filter.setLogin(cozinheiro.getLogin());
//		users = userService.findBy(filter);
//		if (users.isEmpty()) {
//			cozinheiro.setEndereco(getEndereco(city));
//			usuarios.add(cozinheiro);
//		}
//
//		// entregador
//		filter.setLogin(entregador.getLogin());
//		users = userService.findBy(filter);
//		if (users.isEmpty()) {
//			entregador.setEndereco(getEndereco(city));
//			usuarios.add(entregador);
//		}

		// Add
		for (User user : usuarios) {
			endService.save(user.getEndereco());
			userService.save(user);
		}
	}

	private User getUsuarioAdmin() {
		User user = new User();

		user.setLogin("admin");
		user.setSenha("admin");
		user.setTipo(TipoUser.ADMIN);

		user.setNome("Administrador");
		user.setDataDeNascimento(DateUtil.getStartOfDay(new Date()));
		user.setCpf("0");

		return user;
	}

	private User getUsuarioCliente() {
		User user = new User();

		user.setLogin("cliente");
		user.setSenha("cliente");
		user.setTipo(TipoUser.CLIENTE);

		user.setNome("Dona raimunda");
		user.setDataDeNascimento(DateUtil.getStartOfDay(new Date()));
		user.setCpf("107.400.174-51");

		return user;
	}

	private User getUsuarioCozinheiro() {
		User user = new User();

		user.setLogin("cozinheiro");
		user.setSenha("a");
		user.setTipo(TipoUser.COZINHEIRO);

		user.setNome("Manel");
		user.setDataDeNascimento(DateUtil.getStartOfDay(new Date()));
		user.setCpf("107.400.174-52");

		return user;
	}

	private User getUsuarioEntregador() {
		User user = new User();

		user.setLogin("entregador");
		user.setSenha("1234567");
		user.setTipo(TipoUser.ENTREGADOR);

		user.setNome("Zezinho Cabeção");
		user.setDataDeNascimento(DateUtil.getStartOfDay(new Date()));
		user.setCpf("107.400.174-53");

		return user;
	}

	private Endereco getEndereco(City city) {
		Endereco end = new Endereco();
		end.setBairro("Alto Alegre");
		end.setCep("58540-000");
		end.setNumero("144");
		end.setCidade(city);
		end.setRua("Raimundo Sabia");

		return end;
	}

}
