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

	private long codigo;
	
	private Date dataPedidoInicio, dataPedidoFim;

	

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

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public boolean isEmpty() {
		if (this.codigo != 0) {
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
