package banking.rest.application;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankingRestApplication extends ResourceConfig {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public BankingRestApplication() {
		LOGGER.info("Constructing BankingApplication object.");
		// LABTASK: Regsiter your customer resource
		// LABTASK: Register your account resource
		// LABTASK: Add JacksonFeature (register) so you can use JAXB/JSON integration
	}

}
