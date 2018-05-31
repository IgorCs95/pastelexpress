package beans;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Item;
import entities.ItemPedido;
import entities.Pedido;
import services.PedidoService;
import services.ServiceDacException;

@Named
@SessionScoped
public class EditCarrinho extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651571553470606055L;
	
	private ArrayList<ItemPedido> lista;

	@Inject
	private PedidoService serverPedido;

	@PostConstruct
	public void init() {
		lista = new ArrayList<>();
	}

	public void addItem(Item item) {
		lista.add(new ItemPedido(item, 1));

	}

	public boolean carrinhoVazio() {
		if (qtdItems() == 0)
			return false;
		else
			return true;
	}

	public int qtdItems() {
		int cont = 0;

		for (int j = 0; j < lista.size(); j++) {
			cont++;
		}
		return cont;
	}
	
	public float valorTotal() {
		float soma = 0;

		for (int j = 0; j < lista.size(); j++) {
			soma+=lista.get(j).subTotal();
		}
		return soma;
	}

	public String removerItem(ItemPedido item) {
		lista.remove(item);
		
		return "carrinho?faces-redirect=true";

	}

	public void alterarItem(ItemPedido item, int qtd) {
		for (ItemPedido i : lista) {
			if (item.equals(i)) {
				i.setQtd(qtd);
				reportarMensagemDeSucesso("Sucesso");
			}
		}
	}

	public String criarPedido() {
		try {
			Pedido pedido = new Pedido();
			
			pedido.setItems(lista);
			pedido.setData(new Date());

			serverPedido.save(pedido);
		} catch (ServiceDacException e) {
			e.printStackTrace();
		}

		return "||||||pagina inicial quando criada|||||";
	}

	public ArrayList<ItemPedido> getLista() {
		return lista;
	}

	public void setLista(ArrayList<ItemPedido> lista) {
		this.lista = lista;
	}

}
