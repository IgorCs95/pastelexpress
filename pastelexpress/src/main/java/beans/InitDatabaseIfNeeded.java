package beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

import exception.DacRuntimeException;
import services.ServiceDacException;
import services.UsersDataGeneratorService;


@Named
@Eager // Thanks, Omnifaces!!!
@ApplicationScoped
public class InitDatabaseIfNeeded {

	@Inject
	private UsersDataGeneratorService usersDataGeneratorService;
	
	
	@PostConstruct
	public void postConstruct() {
		
		try {
			usersDataGeneratorService.generateData();
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum erro ao tentar inicializar a base de dados.", e);
		}
	}
	
}
