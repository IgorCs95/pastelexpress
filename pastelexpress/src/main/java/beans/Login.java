package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.User;
import exception.PersistenciaDacException;
import services.UserService;
import util.SessionContext;

@Named
@RequestScoped
public class Login extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5642208765891237498L;

	private String senha;

	private String login;

	@Inject
	private SessionContext ses;

	@Inject
	private UserService userService;

	public void init() {
		senha = "";
		login = "";
	}

	/**
	 * Retorna usuario logado
	 */
	public User getUser() {
		return (User) ses.getUsuarioLogado();
	}

	public String getUserName() {
		if (getUser() == null)
			return "VISITANTE";
		else
			return getUser().getNome();
	}

	public String doLogin() {

		try {
			User user = userService.isUsuarioVerificarLogin(login, senha);
			ses.setUsuarioLogado(user);
			return "/index.xhtml?faces-redirect=true";
		} catch (PersistenciaDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return "";
		}
	}

	public String doLogout() {
		ses.encerrarSessao();
		reportarMensagemDeSucesso("Logout realizado com sucesso !");
		return "/telaLogin.xhtml?faces-redirect=true";
	}

	public void solicitarNovaSenha() {
		/*
		 * try { userService().gerarNovaSenha(login, email);
		 * addInfoMessage("Nova Senha enviada para o email " + email); } catch
		 * (BOException e) { addErrorMessage(e.getMessage());
		 * FacesContext.getCurrentInstance().validationFailed(); }
		 */
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
