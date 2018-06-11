package beans.erros;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import beans.AbstractBean;
import exception.DacRuntimeException;


@Named
@RequestScoped
public class ErrorPostConstruct extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		throw new DacRuntimeException("Simulação de erro num método anotado com @PostConstruct");
	}

}
