package beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import entities.User;


@Named
@RequestScoped
public class UserDetails extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5032113942694888619L;
	
	private User usuarioDetalhes;

	@PostConstruct
	public void init() {
		this.usuarioDetalhes = getUsuarioLogado();
	}

	public User getUsuarioDetalhes() {
		return usuarioDetalhes;
	}

	public void setUsuarioDetalhes(User usuarioDetalhes) {
		this.usuarioDetalhes = usuarioDetalhes;
	}

}
