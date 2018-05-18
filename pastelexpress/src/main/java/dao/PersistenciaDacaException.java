package dao;

import exception.DacException;

public class PersistenciaDacaException extends DacException {

	private static final long serialVersionUID = 7159282553688713660L;

	public PersistenciaDacaException(String message) {
		super(message);
	}

	public PersistenciaDacaException(String message, Throwable cause) {
		super(message, cause);
	}

}
