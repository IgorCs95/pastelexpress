package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.Item;
import filter.ItemFilter;
import services.ItemService;
import services.ServiceDacException;

@ViewScoped
@ManagedBean
public class Catalogo extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8598103867635945238L;


	private List<Item> items;

	private ItemService itemService = new ItemService();

	private ItemFilter itemFilter;

	public List<Item> getItems() {
		return items;
	}

	

	public ItemFilter getItemFilter() {
		return itemFilter;
	}



	public void setItemFilter(ItemFilter itemFilter) {
		this.itemFilter = itemFilter;
	}



	@PostConstruct
	public void init() {
		limpar();
		buscarTodos();
	}
	

	public String filtrar() {
		try {
			items = itemService.findBy(getItemFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}
	
	public String buscarTodos() {
		try {
			items = itemService.getAll();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.itemFilter = new ItemFilter();
		return null;
	}
	
	public String delete(Item item) {
		try {
			itemService.delete(item);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Item '" + item.getNome() + "' deletedo");
		
		return "index?faces-redirect=true";
	}

}
