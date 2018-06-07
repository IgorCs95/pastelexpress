package beans;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.fileupload.FileUpload;

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

	private FileUpload foto;

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

		reportarMensagemDeSucesso("Cadastro do Item '" + item.getNome() + "' realizado com sucesso.");

		init();

		return "gerencia_items?faces-redirect=true";
	}

	public FileUpload getFoto() {
		return foto;
	}

	public void setFoto(FileUpload foto) {
		this.foto = foto;
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
