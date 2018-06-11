package beans.erros;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import beans.AbstractBean;
import exception.DacException;


@Named
@RequestScoped
public class ErrorAction extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void action() throws DacException {
		throw new DacException("Simulação de erro no método chamado por um 'action'");
	}
}
