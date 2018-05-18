package filter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author IgorCs
 *
 */
public class CarrinhoFilter implements Serializable, Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7610160282096330937L;

	private Integer id;

	private Date dataInicio, dataFim;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public boolean isEmpty() {

		if (this.id != null) {
			return false;
		}
		if (this.dataInicio != null) {
			return false;
		}
		if (this.dataFim != null) {
			return false;
		}
		return true;
	}

}
