package services;

import exception.DacException;

public class ServiceDacException extends DacException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4792029220018581437L;

	public ServiceDacException(String mensagem) {
		super(mensagem);
	}

	public ServiceDacException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
