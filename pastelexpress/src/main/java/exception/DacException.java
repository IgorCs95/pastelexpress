package exception;



public class DacException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8818769666590433036L;

	public DacException(String mensagem) {
		super(mensagem);
	}

	public DacException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}


}
