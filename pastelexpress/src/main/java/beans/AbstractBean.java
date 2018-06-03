package beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import ennum.StatusCompra;
import ennum.TipoPagamento;
import ennum.TipoUser;


public abstract class AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -332377750642157137L;

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
