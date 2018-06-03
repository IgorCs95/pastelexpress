package filter;

import java.io.Serializable;
import java.util.Date;


/**
 * @author IgorCs
 *
 */
public class PedidoFilter implements Serializable, Filter {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1649567203355718464L;

	private Date dataPedidoInicio, dataPedidoFim;

	private Integer idUser;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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
		if (this.idUser != 0) {
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
