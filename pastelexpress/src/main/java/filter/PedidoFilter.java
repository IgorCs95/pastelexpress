package filter;

import java.io.Serializable;
import java.util.Date;


/**
 * @author IgorCs
 *
 */
public class PedidoFilter implements Serializable,Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5459543345042621500L;

	private Integer id;

	private Date dataPedidoInicio, dataPedidoFim;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataPedidoInicio() {
		return dataPedidoInicio;
	}

	public void setDataPedidoInicio(Date dataPedidoInicio) {
		this.dataPedidoInicio = dataPedidoInicio;
	}

	public Date getDataPedidoFim() {
		return dataPedidoFim;
	}

	public void setDataPedidoFim(Date dataPedidoFim) {
		this.dataPedidoFim = dataPedidoFim;
	}

	public boolean isEmpty() {
		if (this.id != 0) {
			return false;
		}

		if (this.dataPedidoInicio != null) {
			return false;
		}

		if (this.dataPedidoFim != null) {
			return false;
		}

		return true;
	}

}
