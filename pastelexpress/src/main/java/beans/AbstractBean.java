package beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import ennum.StatusCompra;
import ennum.TipoPagamento;
import ennum.TipoUser;
import entities.User;
import filter.UserFilter;
import services.ServiceDacException;
import services.UserService;

public abstract class AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -332377750642157137L;

	@Inject
	private UserService userService;

	protected void reportarMensagemDeErro(String detalhe) {
		reportarMensagem(true, detalhe);

	}

	protected void reportarMensagemDeSucesso(String detalhe) {
		reportarMensagem(false, detalhe);
	}

	private void reportarMensagem(boolean isErro, String detalhe) {
		String sumario = "Success!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (isErro) {
			sumario = "Error!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, sumario, detalhe);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public boolean isUserInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
	}

	public boolean isUserFunc() {
		if (isUserInRole("ADMIN")) {
			return true;
		}
		if (isUserInRole("COZINHEIRO")) {
			return true;
		}
		if (isUserInRole("ENTREGADOR")) {
			return true;
		} else {
			return false;
		}

	}

	public String getUserLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal == null) {
			return "";
		}

		return userPrincipal.getName();
	}

	public User getUsuarioLogado() {
		UserFilter filter = new UserFilter();
		filter.setLogin(getUserLogin());
		List<User> users = null;
		try {
			users = userService.findBy(filter);
		} catch (ServiceDacException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Erro ao recuperar o usuário logado!");
		}

		if (!users.isEmpty()) {
			return users.get(0);
		}

		return null;
	}

	public TipoUser[] getTiposUser() {
		return TipoUser.values();
	}

	public StatusCompra[] getStatusCompra() {
		return StatusCompra.values();
	}

	public TipoPagamento[] getTipoCompra() {
		return TipoPagamento.values();
	}

}
