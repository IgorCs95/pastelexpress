package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.paginas.EnderecoPaginas;
import ennum.TipoUser;
import entities.City;
import entities.Endereco;
import entities.State;
import entities.User;
import filter.UserFilter;
import services.CityService;
import services.EnderecoService;
import services.ServiceDacException;
import services.StateService;
import services.UserService;

@Named
@ViewScoped
public class GerenciarUser extends AbstractBean {

	/**
	 * 
	 */

	private static final long serialVersionUID = -3516569960085314997L;

	// entidades
	private User user;

	private Endereco end;

	private State selectedState;

	// serviços
	@Inject
	private UserService userService;

	@Inject
	private StateService stateService;

	@Inject
	private CityService cityService;

	@Inject
	private EnderecoService enderecoService;

	// listas
	private List<State> states;

	private List<City> cities;

	private List<User> lista;

	// filtros
	private UserFilter userFilter;

	// -----------------------------------Metodos--------------------------------------------------------------

	public void carregarPerfilLogado() {
		user = getUsuarioLogado();
		init();
	}

	public boolean isUserLogado() {
		return user != null;
	}

	public void init() {
		try {
			// Criando novo usuário
			if (user == null) {
				user = new User();
				end = new Endereco();
			}
			// Editando usuário já existente
			else {
				end = user.getEndereco();

				selectedState = getState(user);
				cities = cityService.findBy(selectedState);
			}

			states = stateService.getAll();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveUser() {
		salvar();
		return EnderecoPaginas.PAGINA_G_EDIT_USER;
	}

	public String saveUserPerfil() {
		salvar();
		return "/protegido/perfil?faces-redirect=true";
	}

	private void salvar() {
		try {
			if (isEdicaoDeUsuario()) {
				user.setEndereco(end);

				enderecoService.update(end);

				userService.update(user, false);
			} else {
				userService.validarCpf(user);
				userService.validarLogin(user);

				enderecoService.save(end);

				user.setEndereco(end);
				userService.save(user);
			}
			reportarMensagemDeSucesso("Cadastro do Usuario: " + user.getNome() + " realizado com suceso.");
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public void checarDisponibilidadeLogin() {
		try {
			userService.validarLogin(user);
			reportarMensagemDeSucesso(String.format("Login '%s' e valido.", user.getLogin()));
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public void checarcpf() {
		try {
			userService.validarCpf(user);
			reportarMensagemDeSucesso(String.format("CPF '%s' é valido.", user.getCpf()));
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public void loadCities() {
		try {
			if (selectedState != null) {
				cities = this.cityService.findBy(selectedState);
			} else {
				cities = new ArrayList<>();
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String filtrar() {
		try {
			if (getUserFilter() == null) {
				lista = userService.getAll();
			} else {
				lista = userService.findBy(getUserFilter());
			}

		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.userFilter = new UserFilter();
		user = new User();
		return null;
	}

	public void delete(User user) {
		try {
			userService.delete(user);
			enderecoService.delete(user.getEndereco());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}

		reportarMensagemDeSucesso("User '" + user.getNome() + "' Removido com sucesso.");

	}

	// -----------------------------------Auxiliares--------------------------------------------------------------

	private State getState(User user) {
		if (user.getEndereco().getCidade() == null) {
			return null;
		}
		return user.getEndereco().getCidade().getState();
	}

	public boolean isEdicaoDeUsuario() {
		return user != null && user.getId() != null;
	}

	public boolean isEntregador() {
		return user != null && user.getTipo() == TipoUser.ENTREGADOR;
	}

	public boolean isGerencia() {
		return user != null && user.getTipo() == TipoUser.ADMIN;
	}

	public boolean isCozinheiro() {
		return user != null && user.getTipo() == TipoUser.COZINHEIRO;
	}

	public boolean isCliente() {
		return user != null && user.getTipo() == TipoUser.CLIENTE;
	}

	public boolean isStateSelected() {
		return selectedState != null;
	}

	// -----------------------------------Gets e
	// Sets--------------------------------------------------------------

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getLista() {
		return lista;
	}

	public void setLista(List<User> lista) {
		this.lista = lista;
	}

	public UserFilter getUserFilter() {
		return userFilter;
	}

	public void setUserFilter(UserFilter userFilter) {
		this.userFilter = userFilter;
	}

	public User getUser() {
		return user;
	}

	public State getSelectedState() {
		return selectedState;
	}

	public void setSelectedState(State selectedState) {
		this.selectedState = selectedState;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

}
