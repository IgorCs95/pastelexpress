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
import services.CityService;
import services.EnderecoService;
import services.ServiceDacException;
import services.StateService;
import services.UserService;


@Named
@ViewScoped
public class CriarUserCliente extends AbstractBean {

	
	private static final long serialVersionUID = -8084039557753245768L;

	private User user;
	
	private Endereco end;

	private State selectedState;

	private List<State> states;

	private List<City> cities;
	
	@Inject
	private EnderecoService ends;

	@Inject
	private UserService userService;

	@Inject
	private StateService stateService;

	@Inject
	private CityService cityService;

	public void init() {
		// Inicializar dados do usuário
		user = new User();
		end = new Endereco();
		user.setTipo(TipoUser.CLIENTE);;

		try {
			states = stateService.getAll();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveUser() {
		try {
			// Criação de novo usuário
			
			userService.validarCpf(user);
			userService.validarLogin(user);
			
			ends.save(end);
			
			user.setEndereco(end);
			
			userService.save(user);
			reportarMensagemDeSucesso("Usuário '" + user.getNome() + "' criado com sucesso!");
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}

	public void checarDisponibilidadeLogin() {
		try {
			userService.validarLogin(user);
			reportarMensagemDeSucesso(String.format("Login '%s' is available.", user.getLogin()));
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
