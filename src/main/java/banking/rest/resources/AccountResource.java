package banking.rest.resources;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import banking.dao.AccountDao;
import banking.dao.mapimpl.AccountDaoMapImpl;

// LABTASK: Use appropriate Path to map this resource
public class AccountResource {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public AccountResource() {
		LOGGER.info("Constructing AccountResource object.");
	}
	
	private AccountDao dao = new AccountDaoMapImpl();
	
	// LABTASK: Use appropriate Path and methods for this task
	public Response getAccounts() {
		// LABTASK: Use dao to find all accounts
		// LABTASK: Build appropriate response and return back
		return null;
	}

	// LABTASK: Use appropriate Path and methods for this task
	// LABTASK: Insert @PathParam so you can inject appropriate id from the path
	public Response getAccount(Integer id) {
		// LABTASK: Get an account from dao
		// LABTASK: Throw an exception if the account was not found, return approrpirate response NOT_FOUND
		// LABTASK: Build appropriate response and return back
		return null;
	}

}
