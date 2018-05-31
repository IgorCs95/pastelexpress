package beans;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Item;
import services.ItemService;
import services.ServiceDacException;

@Named
@ViewScoped
public class EditItem extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6236852536417871637L;

	private Item item;

	@Inject
	private ItemService itemService;

	public void init() {
		if (item == null) {
			item = new Item();
		}
	}
	

	public String saveItem() {
		try {
			if (isEdicao()) {
				itemService.update(item);
			} else {
				itemService.save(item);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Cadartro do Item '" + item.getNome() + "' realizado com sucesso.");

		return "index.xhtml?faces-redirect=true";
	}

	public Item getItem() {
		return item;
	}
	

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isEdicao() {
		return item != null && item.getId() != null;
	}

}
