package banking.rest.application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import banking.rest.resources.AccountResource;
import banking.rest.resources.CustomerResource;

public class BankingRestApplication extends ResourceConfig {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public BankingRestApplication() {
		LOGGER.info("Constructing BankingApplication object.");
		register(CustomerResource.class);
		register(AccountResource.class);
		register(JacksonFeature.class);
	}

}
