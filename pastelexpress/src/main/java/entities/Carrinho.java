package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CARRINHO")
public class Carrinho implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -23214071235699839L;

	@Id
	private Integer id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "carrinho_id")
	private Collection<ItemPedido> items;
	
	private Date data;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ItemPedido> getItems() {
		return items;
	}

	public void setItems(Collection<ItemPedido> items) {
		this.items = items;
	}

	public void addItemPedido(Item item,int qtd) {
		items.add(new ItemPedido(item, qtd));
	}
	
	public void removerItem(ItemPedido item) {
		items.remove(item);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public float total() {
		float soma = 0;
		for (ItemPedido itemPedido : items) {
			soma +=itemPedido.subTotal();
		}
		return soma;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		Carrinho other = (Carrinho) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}
	
	public Carrinho clone() {
		Carrinho clone = new Carrinho();
		
		clone.setData(data);
		clone.setId(id);
		clone.setItems(items);
		
		return clone;
	}
	
	
	
	

}
