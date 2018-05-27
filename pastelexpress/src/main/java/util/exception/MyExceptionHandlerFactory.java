package util.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;


/**
 * Referência: https://community.oracle.com/blogs/edburns/2009/09/03/dealing-gracefully-viewexpiredexception-jsf2
 */
public class MyExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;
	
	public MyExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}
	
	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler result = parent.getExceptionHandler();
        result = new MyExceptionHandler(result);
		return result;
	}

}
