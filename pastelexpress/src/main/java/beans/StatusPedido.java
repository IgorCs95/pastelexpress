package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Pedido;
import entities.User;
import filter.PedidoFilter;
import services.PedidoService;
import services.ServiceDacException;
import util.SessionContext;

@Named
@RequestScoped
public class StatusPedido extends AbstractBean {
	private static final long serialVersionUID = -3364766664352990789L;

	@Inject
	private PedidoService pedidosService;

	@Inject
	private SessionContext ses;

	private Pedido pedido;

	private PedidoFilter pfilter;

	@PostConstruct
	public void init() {
		pedido = new Pedido();
		pfilter = new PedidoFilter();
	}

	public List<Pedido> allPedidos() {
		try {
			User user = ses.getUsuarioLogado();

			pfilter.setIdUser(user.getId());

			pedidosService.findBy(pfilter);
		} catch (ServiceDacException e) {
			e.printStackTrace();
		}

		return null;
	}

	// ------------------------------------------------
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
