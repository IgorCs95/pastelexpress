package filter;

import java.io.Serializable;
import java.util.Date;

import entities.User;


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

	private User idUser;

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
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
		if (this.idUser != null) {
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
