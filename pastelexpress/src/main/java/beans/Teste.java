package beans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import entities.Carrinho;
import entities.City;
import entities.Item;
import entities.ItemPedido;
import entities.State;
import entities.User;

@SessionScoped
@ManagedBean
public class Teste extends AbstractBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -363204542804611782L;

	private static Carrinho car;
	
	private static User user;
	
	private static City cidade;
	
	private static State estado;
	
	private static ArrayList<Item> lista= new ArrayList<>();
	
	private static Item item;
	
	static {
		car = new Carrinho();
		user = new User();
		cidade = new City();
		estado = new State();
		item = new Item();
		
		item.setNome("Nome do Item");
		item.setValor(100);
		item.setCodigo(1);
		item.setDescricao("Descrição do Item");
		item.setId(1);
		
		
		lista.add(new Item(1, 001, "Item 1", 200, "descriçao"));
		lista.add(new Item(2, 002, "Item 2", 200, "descriçao"));
		lista.add(new Item(3, 003, "Item 3", 200, "descriçao"));
		lista.add(new Item(4, 004, "Item 4", 200, "descriçao"));
		lista.add(new Item(5, 005, "Item 5", 200, "descriçao"));
		lista.add(new Item(6, 006, "Item 6", 200, "descriçao"));
		
		lista.add(new Item(7, 001, "Item 1", 200, "descriçao"));
		lista.add(new Item(8, 002, "Item 2", 200, "descriçao"));
		lista.add(new Item(9, 003, "Item 3", 200, "descriçao"));
		lista.add(new Item(10, 004, "Item 4", 200, "descriçao"));
		lista.add(new Item(11, 005, "Item 5", 200, "descriçao"));
		lista.add(new Item(12, 006, "Item 6", 200, "descriçao"));
		
		lista.add(new Item(13, 001, "Item 1", 200, "descriçao"));
		lista.add(new Item(14, 002, "Item 2", 200, "descriçao"));
		lista.add(new Item(15, 003, "Item 3", 200, "descriçao"));
		lista.add(new Item(16, 004, "Item 4", 200, "descriçao"));
		lista.add(new Item(17, 005, "Item 5", 200, "descriçao"));
		lista.add(new Item(18, 006, "Item 6", 200, "descriçao"));
		
		
	}
	

	public void salvarItem() {
		System.out.println("Item Adicionado ------- "+item+"------");
		lista.add(item);
		item = null;
	}
	
	public Carrinho getCar() {
		return car;
	}

	public void setCar(Carrinho car) {
		this.car = car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public City getCidade() {
		return cidade;
	}

	public void setCidade(City cidade) {
		this.cidade = cidade;
	}

	public State getEstado() {
		return estado;
	}

	public void setEstado(State estado) {
		this.estado = estado;
	}

	public ArrayList<Item> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Item> lista) {
		this.lista = lista;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isEdicaoDeItem() {
		return item==null;
	}


	public boolean isEdicaoDeUser() {
		return user==null;
	}

	
	
	
	
	
	

}
