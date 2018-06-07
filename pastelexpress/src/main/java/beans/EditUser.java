package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ennum.TipoUser;
import entities.City;
import entities.Endereco;
import entities.State;
import entities.User;
import services.CityService;
import services.EnderecoService;
import services.ServiceDacException;
import services.StateService;
import services.UserService;
import util.SessionContext;

@Named
@ViewScoped
public class EditUser extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3516569960085314997L;

	private User user;
	
	@Inject
	private UserService userService;

	@Inject
	private StateService stateService;

	@Inject
	private CityService cityService;
	
	@Inject
	private EnderecoService enderecoService;

	private Endereco end;

	private State selectedState;

	private List<State> states;

	private List<City> cities;
	
	@Inject
	private SessionContext ses;
	
	public void carregarPerfil() {
		user = ses.getUsuarioLogado();
		init();
		
	}
	
	public boolean userLogado() {
		return user!=null;
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

	public void saveUser() {
		try {
			if (isEdicaoDeUsuario()) {
				if(end!=null && user.getEndereco()!=null && !user.getEndereco().equals(end)) {
					user.setEndereco(end);
					enderecoService.update(end);
				}
				userService.update(user, false);
			} else {
				
				enderecoService.save(end);
				
				user.setEndereco(end);
				userService.save(user);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}

		reportarMensagemDeSucesso("Cadastro do Usuario: " + user.getNome() + " realizado com suceso.");

	}

	public void checarDisponibilidadeLogin() {
		try {
			userService.validarLogin(user);
			reportarMensagemDeSucesso(String.format("Login '%s' e valido.", user.getLogin()));
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

	private State getState(User user) {
		if (user.getEndereco().getCidade() == null) {
			return null;
		}
		return user.getEndereco().getCidade().getState();
	}

	public boolean isEdicaoDeUsuario() {
		return user != null && user.getId() != null;
	}

	public boolean isFuncionario() {
		return user != null && user.getTipo() == TipoUser.FUNCIONARIO;
	}

	public boolean isCliente() {
		return user != null && user.getTipo() == TipoUser.CLIENTE;
	}

	public boolean isStateSelected() {
		return selectedState != null;
	}

	public void setUser(User user) {
		this.user = user;
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
