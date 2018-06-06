package entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ITEM_PEDIDO")
public class ItemPedido implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_item", foreignKey = @ForeignKey(name = "fk__tb_item_pedido__tb_item"))
	private Item item;

	private float valorItem;

	private int qtd;

	public ItemPedido() {
	}

	public ItemPedido(Item item, int qtd) {
		this.item = item;
		this.qtd = qtd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public float getValorItem() {
		return valorItem;
	}

	public void setValorItem(float valorItem) {
		this.valorItem = valorItem;
	}

	public float subTotal() {
		return valorItem * qtd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + qtd;
		result = prime * result + Float.floatToIntBits(valorItem);
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (qtd != other.qtd)
			return false;
		if (Float.floatToIntBits(valorItem) != Float.floatToIntBits(other.valorItem))
			return false;
		return true;
	}

	@Override
	public ItemPedido clone() {
		ItemPedido clone = new ItemPedido();
		clone.setId(id);
		clone.setItem(item);
		clone.setQtd(qtd);
		clone.setValorItem(valorItem);

		return clone;
	}

	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", item=" + item + ", valorItem=" + valorItem + ", qtd=" + qtd + "]";
	}

}
