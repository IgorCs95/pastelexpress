package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ennum.OrdenarItem;
import entities.Item;
import filter.ItemFilter;
import services.ItemService;
import services.ServiceDacException;

@Named
@ViewScoped
public class Catalogo extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8598103867635945238L;

	private List<Item> items;

	@Inject
	private ItemService itemService;

	private ItemFilter itemFilter;


	private String ordenado;

	private void ordenando() {
		if (ordenado != null) {

			if (ordenado.equals(OrdenarItem.MAIOR.name())) {
				itemFilter.setOrdenarMenorPreco(false);
				itemFilter.setOrdenarMaiorPreco(true);
			}
			if (ordenado.equals(OrdenarItem.MENOR.name())) {
				itemFilter.setOrdenarMaiorPreco(false);
				itemFilter.setOrdenarMenorPreco(true);
			}
		} else {
			itemFilter.setOrdenarMaiorPreco(false);
			itemFilter.setOrdenarMenorPreco(false);

		}
	}

	public String getOrdenado() {
		return ordenado;
	}

	public void setOrdenado(String ordenado) {
		this.ordenado = ordenado;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
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
			ordenando();
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
		filtrar();
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
