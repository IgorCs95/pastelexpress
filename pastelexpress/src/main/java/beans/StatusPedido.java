package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.paginas.EnderecoPaginas;
import ennum.StatusCompra;
import entities.Pedido;
import entities.User;
import filter.PedidoFilter;
import services.PedidoService;
import services.ServiceDacException;
import services.UserService;

@Named
@ViewScoped
public class StatusPedido extends AbstractBean {
	private static final long serialVersionUID = -3364766664352990789L;

	@Inject
	private PedidoService pedidosService;

	private Pedido pedido;

	List<Pedido> lista;

	private PedidoFilter pfilter;

	@Inject
	private UserService UserService;

	private int userBuscado;
	
	

	@PostConstruct
	public void post() {
		userBuscado = 0;
		lista = new ArrayList<>();
		pedido = new Pedido();
		pfilter = new PedidoFilter();

	}

	public void initClient() {
		User user = getUsuarioLogado();
		pfilter.setIdUser(user);
		buscar();
	}

	public void initFunc() {
		pedido = null;
		pfilter.setStatus(StatusCompra.PROCESSANDO);
		buscar();
	}

	public void buscar() {
		try {
			lista = pedidosService.findBy(pfilter);
		} catch (ServiceDacException e) {
			e.printStackTrace();
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public void limpar() {
		pfilter = new PedidoFilter();
		buscar();
	}

	public void getUserID() {
		if (userBuscado != 0) {
			User a = null;
			try {
				a = UserService.getByID(userBuscado);
				if (a == null) {
					pfilter.setIdUser(null);
					reportarMensagemDeErro("Usuario nao Cadastrado");
				} else {
					pfilter.setIdUser(a);
				}
			} catch (ServiceDacException e) {
				e.printStackTrace();
				reportarMensagemDeErro(e.getMessage());
			}
		} else {
			pfilter.setIdUser(null);
		}
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
		} else {
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

	public int getUserBuscado() {
		return userBuscado;
	}

	public void setUserBuscado(int userBuscado) {
		this.userBuscado = userBuscado;
	}
	

}
