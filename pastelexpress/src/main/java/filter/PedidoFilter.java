package filter;

import java.io.Serializable;
import java.util.Date;

import ennum.StatusCompra;
import ennum.TipoPagamento;
import entities.User;


/**
 * @author IgorCs
 *
 */
public class PedidoFilter implements Serializable, Filter {
	
	private static final long serialVersionUID = -1649567203355718464L;
	
	private Date dataPedidoInicio, dataPedidoFim;
	
	private User idUser;
	
	private StatusCompra status;
	
	private TipoPagamento tipo;
	
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
	
	public StatusCompra getStatus() {
		return status;
	}

	public void setStatus(StatusCompra status) {
		this.status = status;
	}
	
	public TipoPagamento getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
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
		
		if (this.status != null) {
			return false;
		}
		
		if (this.tipo != null) {
			return false;
		}

		return true;
	}

}
