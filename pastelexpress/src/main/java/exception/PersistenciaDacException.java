package exception;


public class PersistenciaDacException extends DacException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3040291153609555448L;

	public PersistenciaDacException(String mensagem) {
		super(mensagem);
	}

	public PersistenciaDacException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
