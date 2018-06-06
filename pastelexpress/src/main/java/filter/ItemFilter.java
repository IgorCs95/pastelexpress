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



	private float valorMinimo, valorMaximo;

	private String nome;


	public float getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(float valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public float getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(float valorMaximo) {
		this.valorMaximo = valorMaximo;
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
		if (this.valorMinimo != 0) {
			return false;
		}
		if (this.valorMaximo != 0) {
			return false;
		}

		return true;
	}

}
