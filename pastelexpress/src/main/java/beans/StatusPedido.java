package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.paginas.EnderecoPaginas;
import entities.Pedido;
import entities.User;
import filter.PedidoFilter;
import services.PedidoService;
import services.ServiceDacException;

@Named
@ViewScoped
public class StatusPedido extends AbstractBean {
	private static final long serialVersionUID = -3364766664352990789L;

	@Inject
	private PedidoService pedidosService;


	private Pedido pedido;

	List<Pedido> lista;

	private PedidoFilter pfilter;

	public void initClient() {
		lista = new ArrayList<>();
		pedido = new Pedido();
		pfilter = new PedidoFilter();

		try {
			User user = getUsuarioLogado();

			pfilter.setIdUser(user);

			lista = pedidosService.findBy(pfilter);

		} catch (ServiceDacException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Erro ao buscar pedidos");
		}
	}

	public void initFunc() {
		try {
			lista = new ArrayList<>();
			lista = pedidosService.getAll();
		} catch (ServiceDacException e) {
			e.printStackTrace();
			reportarMensagemDeErro(e.getMessage());
		}
		if (pedido == null) {
			pedido = new Pedido();
		}
		pfilter = new PedidoFilter();
	}

	public String salvarPedidos() {
		if (pedido != null) {
			try {
				pedidosService.update(pedido);
				
				reportarMensagemDeSucesso("Estado Alterado");
				
				return EnderecoPaginas.PAGINA_F_LISTA_PEDIDOS;
			} catch (ServiceDacException e) {
				e.printStackTrace();
				reportarMensagemDeErro(e.getMessage());
			}
		}else {
			try {
				pedidosService.save(pedido);
				reportarMensagemDeSucesso("Pedidos Salvo com Sucesso.");
				
				return EnderecoPaginas.PAGINA_F_LISTA_PEDIDOS;
			} catch (ServiceDacException e) {
				e.printStackTrace();
				reportarMensagemDeErro(e.getMessage());
			}
		}
		return "";

	}

	// ------------------------------------------------
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public PedidoService getPedidosService() {
		return pedidosService;
	}

	public void setPedidosService(PedidoService pedidosService) {
		this.pedidosService = pedidosService;
	}

	public List<Pedido> getLista() {
		return lista;
	}

	public void setLista(List<Pedido> lista) {
		this.lista = lista;
	}

	public PedidoFilter getPfilter() {
		return pfilter;
	}

	public void setPfilter(PedidoFilter pfilter) {
		this.pfilter = pfilter;
	}

}
