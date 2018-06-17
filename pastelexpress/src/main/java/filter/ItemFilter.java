package filter;

import java.io.Serializable;

/**
 * @author IgorCs
 *
 */
public class ItemFilter implements Serializable, Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8779495497577783872L;

	private boolean ordenarMenorPreco, ordenarMaiorPreco;

	private String nome;

	

	public boolean isOrdenarMenorPreco() {
		return ordenarMenorPreco;
	}

	public void setOrdenarMenorPreco(boolean ordenarMenorPreco) {
		this.ordenarMenorPreco = ordenarMenorPreco;
	}

	public boolean isOrdenarMaiorPreco() {
		return ordenarMaiorPreco;
	}

	public void setOrdenarMaiorPreco(boolean ordenarMaiorPreco) {
		this.ordenarMaiorPreco = ordenarMaiorPreco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public boolean isEmpty() {
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		
		if(this.ordenarMenorPreco==false) {
			return false;
		}
		
		if(this.ordenarMenorPreco==false) {
			return false;
		}
		

		return true;
	}

}
