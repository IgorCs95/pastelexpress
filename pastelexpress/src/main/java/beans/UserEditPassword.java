package beans;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.paginas.EnderecoPaginas;
import entities.User;
import services.ServiceDacException;
import services.UserService;


@Named
@ViewScoped
public class UserEditPassword extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4244039822180927305L;

	private User user;

	private String confirmacaoPasswordAtual;

	protected String passwordAtualHash;

	@Inject
	private UserService userService;

	public void armazenarSenhaAtualDoUsuario() {
		passwordAtualHash = this.user.getSenha();
	}

	public String changePassword() {

		try {
			// Confirmar que senha atual equivale a armazenada
			if (!senhaAtualConfere()) {
				reportarMensagemDeErro("A senha informada esta incorreta.");
				return null; // Permanecer na mesma página
			}

			userService.update(user, true);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Senha alterada com sucesso '" + user.getNome() + "'");
		
		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}

	private boolean senhaAtualConfere() throws ServiceDacException {
		
		return userService.senhaAtualConfere(passwordAtualHash, getConfirmacaoPasswordAtual());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmacaoPasswordAtual() {
		return confirmacaoPasswordAtual;
	}

	public void setConfirmacaoPasswordAtual(String confirmacaoPasswordAtual) {
		this.confirmacaoPasswordAtual = confirmacaoPasswordAtual;
	}

}
