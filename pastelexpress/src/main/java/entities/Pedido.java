package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ennum.StatusCompra;
import ennum.TipoPagamento;

@Entity
@Table(name = "TB_PEDIDO")
public class Pedido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2523209373447412818L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Carrinho carrinho;
	
	private Date data;
	
	private StatusCompra estado;
	
	private TipoPagamento pagamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusCompra getEstado() {
		return estado;
	}

	public void setEstado(StatusCompra estado) {
		this.estado = estado;
	}

	public TipoPagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(TipoPagamento pagamento) {
		this.pagamento = pagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrinho == null) ? 0 : carrinho.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + Float.floatToIntBits(id);
		result = prime * result + ((pagamento == null) ? 0 : pagamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (carrinho == null) {
			if (other.carrinho != null)
				return false;
		} else if (!carrinho.equals(other.carrinho))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (estado != other.estado)
			return false;
		if (Float.floatToIntBits(id) != Float.floatToIntBits(other.id))
			return false;
		if (pagamento != other.pagamento)
			return false;
		return true;
	}
	
	@Override
	public Pedido clone() {
		Pedido clone = new Pedido();
		clone.setId(id);
		clone.setCarrinho(carrinho);
		clone.setData(data);
		clone.setEstado(estado);
		clone.setPagamento(pagamento);
		
		return clone;
	}
	
	
	

}
