package filter;

import java.io.Serializable;

/**
 * @author IgorCs
 *
 */
public class ItemPedidoFilter implements Serializable, Filter {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7258291929954309734L;
	private Integer idPedido;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public boolean isEmpty() {

		if (this.idPedido != 0) {
			return false;
		}

		return true;
	}

}
