package beans;


import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.Carrinho;
import entities.Item;
import entities.ItemPedido;
import entities.Pedido;
import services.PedidoService;
import services.ServiceDacException;


@ViewScoped
@ManagedBean
public class EditCarrinho extends AbstractBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1651571553470606055L;
	private Carrinho carrinho;
	private PedidoService serverPedido;
	
	public void addItem(Item item,int qtd){
		carrinho.addItemPedido(item,qtd);
		
	}
	
	public void removerItem(ItemPedido item) {
		carrinho.removerItem(item);
		reportarMensagemDeSucesso("Erro");
		
	}
	public void alterarItem(ItemPedido item,int qtd) {
		for (ItemPedido i : carrinho.getItems()) {
			if(item.equals(i)) {
				i.setQtd(qtd);
				reportarMensagemDeSucesso("Sucesso");
			}
		}
		reportarMensagemDeErro("Erro");
	}
	
	public String criarPedido() {
		try {
			Pedido pedido = new Pedido();
			pedido.setCarrinho(carrinho);
			pedido.setData(new Date());
			
			serverPedido.save(pedido);
		} catch (ServiceDacException e) {
			e.printStackTrace();
		}
		
		return "||||||pagina inicial quando criada|||||";
	}
	
	
	

}
