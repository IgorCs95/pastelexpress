package beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.User;
import exception.PersistenciaDacException;
import services.UserService;
import util.SessionContext;

@Named
@SessionScoped
public class Login extends AbstractBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5642208765891237498L;

	
	
	private String senha;
	
	private String login;
	
	@Inject
	private UserService userService;
	
	
	

  
  
     /**
      * Retorna usuario logado
      * */
     public User getUser() {
        return (User) SessionContext.getInstance().getUsuarioLogado();
     }
  
     public String doLogin() {
    	 System.out.println(login+"---senha---"+senha);
    	 
        try {
            User user = userService.isUsuarioVerificarLogin(login, senha);
  
            if (user == null) {
              reportarMensagemDeErro("Login ou Senha errado, tente novamente !");
              return "";
            }
  
            SessionContext.getInstance().setAttribute("usuarioLogado", user.getNome());
            return "/index.xhtml?faces-redirect=true";
        } catch (PersistenciaDacException e) {
            reportarMensagemDeErro(e.getMessage());
            e.printStackTrace();
            return "";
        }
  
     }
  
     public String doLogout() {
        SessionContext.getInstance().encerrarSessao();
        reportarMensagemDeSucesso("Logout realizado com sucesso !");
        return "/security/form_login.xhtml?faces-redirect=true";
     }
  
     
     public void solicitarNovaSenha() {
       /* try {
        	userService().gerarNovaSenha(login, email);
            addInfoMessage("Nova Senha enviada para o email " + email);
        } catch (BOException e) {
            addErrorMessage(e.getMessage());
            FacesContext.getCurrentInstance().validationFailed();
        }*/
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
