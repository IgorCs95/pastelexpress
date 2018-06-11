package beans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.paginas.EnderecoPaginas;
import ennum.StatusCompra;
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

	private Pedido pedido;


	@Inject
	private PedidoService serverPedido;

	@PostConstruct
	public void init() {
		pedido = new Pedido();
		pedido.setUser(getUsuarioLogado());
	}

	public void addItem(Item item) {
		boolean cont = true;

		for (ItemPedido i : pedido.getItems()) {
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

			pedido.addItem(i);
		}
		reportarMensagemDeSucesso(item.getNome() + " Adicoonado No carrinho.");

	}

	public String removerItem(ItemPedido item) {
		reportarMensagemDeSucesso(item.getItem().getNome() + " removido.");
		pedido.romoverItem(item);

		return "carrinho?faces-redirect=true";

	}

	/**
	 * Metodo para salvar o pedido na base de dados. adicionamos a lista de items
	 * salvamos a hora que o pedido foi realizado. iniciado o estado primario do
	 * pedido.
	 * 
	 * @return
	 */
	public String criarPedido() {

		if (pedido.getUser() != null) {
			if (!pedido.isListaVazia()) {
				try {
					pedido.setData(new Date());
					pedido.setEstado(StatusCompra.PROCESSANDO);

					serverPedido.save(pedido);

					init();
				} catch (ServiceDacException e) {
					e.printStackTrace();
				}
				reportarMensagemDeSucesso("Pedido criado com sucesso.");
				return EnderecoPaginas.PAGINA_PRINCIPAL;
			} else {
				reportarMensagemDeErro("Lista Vazia");
				return "";
			}
		} else {
			reportarMensagemDeErro("Usuario deve estar Logado");
			return "";

		}
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
