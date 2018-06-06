package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Pedido;
import entities.User;
import filter.PedidoFilter;
import services.PedidoService;
import services.ServiceDacException;
import util.SessionContext;

@Named
@ViewScoped
public class StatusPedido extends AbstractBean {
	private static final long serialVersionUID = -3364766664352990789L;

	@Inject
	private PedidoService pedidosService;

	@Inject
	private SessionContext ses;

	private Pedido pedido;

	List<Pedido> lista = new ArrayList<>();

	private PedidoFilter pfilter;

	@PostConstruct
	public void init() {
		pedido = new Pedido();
		pfilter = new PedidoFilter();

		try {
			User user = ses.getUsuarioLogado();

			pfilter.setIdUser(user);

			lista = pedidosService.findBy(pfilter);
			
		} catch (ServiceDacException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Erro ao buscar pedidos");
		}
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
