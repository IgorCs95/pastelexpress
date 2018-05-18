package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.User;
import services.ServiceDacException;
import services.UserService;

@ViewScoped
@ManagedBean
public class EditUser extends AbstractBean{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3516569960085314997L;


	private boolean login;
	
	private UserService userService = new UserService();
	
	
	private User user;
	
	public void init() {
		if (user == null) {
			// Criando novo usuário
			user = new User();
		}
	}
	public String saveUser() {
		try {
			if (isLogin()) {
				userService.update(user, false);
			} else {
				userService.save(user);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Cadartro do usuario '" + user.getNome() + "' realizado com sucesso.");

		return "index.xhtml?faces-redirect=true";
	}
	
	public void checarDisponibilidadeLogin() {
		try {
			userService.validarLogin(user);
			reportarMensagemDeSucesso(String.format("Login '%s' e valido.", user.getLogin()));
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	

	public boolean isLogin() {
		return login;
	}
	
	public void setLogin(boolean login) {
		this.login = login;
	}
}