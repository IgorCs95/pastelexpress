package beans;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ennum.StatusCompra;
import entities.Item;
import entities.ItemPedido;
import entities.Pedido;
import services.PedidoService;
import services.ServiceDacException;
import util.SessionContext;

@Named
@SessionScoped
public class EditCarrinho extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651571553470606055L;

	private ArrayList<ItemPedido> lista;

	private Pedido pedido;

	@Inject
	private SessionContext ses;

	@Inject
	private PedidoService serverPedido;

	@PostConstruct
	public void init() {
		lista = new ArrayList<>();
		pedido = new Pedido();
		pedido.setUser(ses.getUsuarioLogado());
	}

	public void addItem(Item item) {
		boolean cont = true;

		for (ItemPedido i : lista) {
			if (i.getItem().getId() == item.getId()) {
				i.setQtd(i.getQtd() + 1);
				cont = false;
			}
		}

		if (cont) {
			ItemPedido i = new ItemPedido();
			i.setItem(item);
			i.setQtd(1);
			i.setValorItem(item.getValor());

			lista.add(i);
		}

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
			soma += lista.get(j).subTotal();
		}
		return soma;
	}

	public String removerItem(ItemPedido item) {
		reportarMensagemDeSucesso(item.getItem().getNome() + " removido.");
		lista.remove(item);

		return "carrinho?faces-redirect=true";

	}

	/**Metodo para salvar o pedido na base de dados.
	 * adicionamos a lista de items
	 * salvamos a hora que o pedido foi realizado.
	 * iniciado o estado primario do pedido.
	 * 
	 * @return
	 */
	public String criarPedido() {
		try {
			pedido.setItems(lista);
			pedido.setData(new Date());
			pedido.setEstado(StatusCompra.PROCESSANDO);

			serverPedido.save(pedido);

			init();
		} catch (ServiceDacException e) {
			e.printStackTrace();
		}

		reportarMensagemDeSucesso("Pedido criado com sucesso.");

		return "index?faces-redirect=true";
	}

	public ArrayList<ItemPedido> getLista() {
		return lista;
	}

	public void setLista(ArrayList<ItemPedido> lista) {
		this.lista = lista;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
