package filter;

import java.io.Serializable;

/**
 * @author IgorCs
 *
 */
public class EnderecoFilter implements Serializable,Filter{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8035232127623074717L;

	private String rua, bairro, cep, numero;


	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}


	public boolean isEmpty() {
		if (this.rua != null && !this.rua.trim().isEmpty()) {
			return false;
		}
		if (this.cep != null && !this.cep.trim().isEmpty()) {
			return false;
		}
		if (this.bairro != null && !this.bairro.trim().isEmpty()) {
			return false;
		}
		
		
		if (this.numero != null && !this.numero.trim().isEmpty()) {
			return false;
		}

		return true;
	}

}
