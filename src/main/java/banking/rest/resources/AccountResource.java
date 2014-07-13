package banking.rest.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import banking.dao.AccountDao;
import banking.dao.mapimpl.AccountDaoMapImpl;
import banking.model.Account;

/**
 * AccountResource is a JAX-RS resource representing an account.
 * 
 * It supports the following use cases:
 * 
 * <pre>
 * 
 *  1. Get all accounts
 *  2. Get a single account
 *  3. Delete an account
 *  4. Update an account
 *  5. Create an account
 *  
 * </pre>
 * 
 * @author nevenc
 *
 */
@Path("accounts")
public class AccountResource {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	// We instantiate the dao implementation here.
	// We typically would have container inject an instance of dao implementation.
	private AccountDao dao = new AccountDaoMapImpl();
	
	// JAX-RS servlet injects UriInfo as @Context
	private UriInfo uriInfo;

	/**
	 * AccountResource constructor that is initializes the UriInfo with injected @Context
	 *  
	 * @param uriInfo Injected context path
	 * 
	 */
	public AccountResource(@Context UriInfo uriInfo) {
		LOGGER.info("Constructing AccountResource object.");
		this.uriInfo = uriInfo;
	}
	
	/**
	 * Gets all accounts
	 * 
	 *   GET http://XXXX/accounts
	 *   
	 * @return Response object with a collection of found accounts.
	 */
	@GET
	public Response getAccounts() {
		Collection<Account> accounts = dao.findAccounts();
		return Response.ok(accounts).build();
	}

	/**
	 * Gets a single account.
	 * 
	 *   GET http://XXXX/accounts/{id}
	 * 
	 * @return Response object with a found account (id={id}).
	 *         Response object with 404 status (NOT FOUND) if account not found.
	 * 
	 */
	@GET
	@Path("{id}")
	public Response getAccount(@PathParam("id") Integer id) {
		Account account = dao.findAccountById(id);
		if ( account == null ) return Response.status(Status.NOT_FOUND).build();
		return Response.ok(account).build();
	}

	/**
	 * Creates a new account.
	 * 
	 *   POST http://XXXX/accounts
	 * 
	 * @param account Account object will get created and injected from request body
	 * @return        Response with status 201 (CREATED) and location of the created resource.
	 * 
	 * e.g. Location: http://XXXX/accounts/{newid}
	 */
	@POST
	public Response saveAccount(Account account) {
		LOGGER.info("Account to save: " + account);
		Account saved = dao.saveAccount(account);
		URI location = uriInfo.getAbsolutePathBuilder().path(saved.getId().toString()).build();
		return Response.created(location).build();
	}
	
	
	/**
	 * Updates an account.
	 * 
	 *   PUT http://XXXX/accounts/{id}
	 * 
	 * @param id      Account id will get parsed from URI path
	 * @param account Account object will get created and injected from request body
	 * @return        Response with status 200 (OK) and updated account object.
	 * 
	 */
	@PUT
	@Path("{id}")
	public Response updateAccount(@PathParam("id") Integer id, Account account) {
		account.setId(id);
		LOGGER.info("Account to update: " + account);
		Account updated = dao.updateAccount(account);
		return Response.ok(updated).build();
	}
	
	/**
	 * Removes an account.
	 * 
	 *   DELETE http://XXXX/accounts/{id}
	 * 
	 * @return Response object with status 200 (OK) and deleted account object.
	 * 
	 */
	@DELETE
	@Path("{id}")
	public Response deleteAccount(@PathParam("id") Integer id) {
		Account account = new Account(id,null,0.0,null);
		LOGGER.info("Customer to delete: " + account);
		Account deleted = dao.deleteAccount(account);
		return Response.ok(deleted).build();
	}
	
}
