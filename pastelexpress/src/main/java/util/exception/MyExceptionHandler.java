package util.exception;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.persistence.PersistenceException;

/**
 * Referência: https://community.oracle.com/blogs/edburns/2009/09/03/dealing-gracefully-viewexpiredexception-jsf2
 */
public class MyExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public MyExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable t = context.getException();
			
			// The code below also works and report the root cause to the end user
//			t = getRootCause(t);
//			t = getRecursiveRootCause(t);
//			reportarMensagem(true, t.getMessage());
//			FacesContext fc = FacesContext.getCurrentInstance();
//			fc.renderResponse();
//			i.remove();
			
			if (getRecursiveRootCause(t, PersistenceException.class) instanceof PersistenceException) {
				reportarMensagem(true, "Ocorreu algum erro ao acessar o banco de dados. Favor contactar o administrador!");
				FacesContext fc = FacesContext.getCurrentInstance();
				fc.renderResponse();
				i.remove();
				continue;
			}
			
			if (getRecursiveRootCause(t, ViewExpiredException.class) instanceof ViewExpiredException) {
				reportarMensagem(true, "Sua sessão expirou e você foi redirecionado automaticamente para a página principal!");
				FacesContext fc = FacesContext.getCurrentInstance();
				NavigationHandler nav = fc.getApplication().getNavigationHandler();
				nav.handleNavigation(fc, null, "index");
				fc.renderResponse();
				i.remove();
				continue;
			}
		}
		// At this point, the queue will not contain any ViewExpiredEvents.
		// Therefore, let the parent handle them.
		getWrapped().handle();
	}

	private void reportarMensagem(boolean isErro, String detalhe) {
		String sumario = "Success!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (isErro) {
			sumario = "Error!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, sumario, detalhe);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
	@SuppressWarnings("unused")
	private Throwable getRecursiveRootCause(Throwable e) {
		// Referência: https://stackoverflow.com/a/28565320
		return getRecursiveRootCause(e, null);
	}	
	
	private Throwable getRecursiveRootCause(Throwable e, Class<?> expectedThrowableClassType) {
		// Referência: https://stackoverflow.com/a/28565320
		Throwable cause = null; 
	    Throwable result = e;

	    while((cause = result.getCause()) != null  && (cause != result)) {
	        result = cause;
	        if (expectedThrowableClassType != null 
	        		&& expectedThrowableClassType.isAssignableFrom(cause.getClass())) {
	        	return result;
	        }
	    }
	    return result;
	}	
}
