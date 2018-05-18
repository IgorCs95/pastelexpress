package filter;

import java.io.Serializable;
import java.util.Date;

import ennum.TipoUser;

/**
 * @author IgorCs
 *
 */
public class UserFilter implements Filter,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 700231349635533022L;

	private String nome, login,cpf;
	
	private Date dataNascimentoInicio, dataNascimentoFim;
	
	private TipoUser tipo;
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimentoInicio() {
		return dataNascimentoInicio;
	}

	public void setDataNascimentoInicio(Date dataNascimentoInicio) {
		this.dataNascimentoInicio = dataNascimentoInicio;
	}

	public Date getDataNascimentoFim() {
		return dataNascimentoFim;
	}

	public void setDataNascimentoFim(Date dataNascimentoFim) {
		this.dataNascimentoFim = dataNascimentoFim;
	}
	
	

	public TipoUser getTipo() {
		return tipo;
	}

	public void setTipo(TipoUser tipo) {
		this.tipo = tipo;
	}

	public boolean isEmpty() {
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		if (this.login != null && !this.login.trim().isEmpty()) {
			return false;
		}
		if (this.cpf != null && !this.cpf.trim().isEmpty()) {
			return false;
		}
		if (this.tipo != null) {
			return false;
		}
		if (this.dataNascimentoInicio != null) {
			return false;
		}
		if (this.dataNascimentoFim != null) {
			return false;
		}
		return true;
	}
	
	

}
