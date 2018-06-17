package beans.paginas;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import beans.AbstractBean;

@Named
@RequestScoped
public class ErroPaginaLogin extends AbstractBean {

	private static final long serialVersionUID = 5769269432204249052L;
	
	public void dispararMensagemLoginErro(boolean error){
		if(error){
			reportarMensagemDeErro("Login e/ou senha errada.");
		}
	}

}