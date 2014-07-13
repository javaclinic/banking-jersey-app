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

import banking.dao.CustomerDao;
import banking.dao.mapimpl.CustomerDaoMapImpl;
import banking.model.Account;
import banking.model.Customer;

/**
 * CustomerResource is a JAX-RS resource representing a customer.
 * 
 * It supports the following use cases:
 * 
 * <pre>
 * 
 *  1. Get all customers
 *  2. Get a single customer
 *  3. Create a new customer
 *  4. Update a customer
 *  5. Delete a customer
 *  6. Get all accounts owned by a customer
 * 
 * </pre>
 * 
 * @author nevenc
 *
 */
@Path("customers")
public class CustomerResource {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	// We instantiate the dao implementation here.
	// We typically would have container inject an instance of dao implementation.
	private CustomerDao dao = new CustomerDaoMapImpl();
	
	// JAX-RS servlet injects UriInfo as @Context
	private UriInfo uriInfo;

	/**
	 * CustomerResource constructor that is initializes the UriInfo with injected @Context
	 *  
	 * @param uriInfo Injected context path
	 * 
	 */
	public CustomerResource(@Context UriInfo uriInfo) {
		LOGGER.info("Constructing CustomerResource object.");
		this.uriInfo = uriInfo;
	}
	
	/**
	 * Gets all customers
	 * 
	 *   GET http://XXXX/customers
	 *   
	 * @return Response object with a collection of found customers.
	 */
	@GET
	public Response getCustomers() {
		Collection<Customer> customers = dao.findCustomers();
		return Response.ok(customers).build();
	}

	/**
	 * Gets a single customer.
	 * 
	 *   GET http://XXXX/customers/{id}
	 * 
	 * @return Response object with a found customer (id={id}).
	 *         Response object with 404 status (NOT FOUND) if customer not found.
	 * 
	 */
	@GET
	@Path("{id}")
	public Response getCustomer(@PathParam("id") Integer id) {
		Customer customer = dao.findCustomerById(id);
		if ( customer == null ) return Response.status(Status.NOT_FOUND).build();
		return Response.ok(customer).build();
	}
	
	/**
	 * Creates a new customer.
	 * 
	 *   POST http://XXXX/customers
	 * 
	 * @param customer Customer object will get created and injected from request body
	 * @return         Response with status 201 (CREATED) and location of the created resource.
	 * 
	 * e.g. Location: http://XXXX/customers/{newid}
	 */
	@POST
	public Response saveCustomer(Customer customer) {
		LOGGER.info("Customer to save: " + customer);
		Customer saved = dao.saveCustomer(customer);
		URI location = uriInfo.getAbsolutePathBuilder().path(saved.getId().toString()).build();
		return Response.created(location).build();
	}

	/**
	 * Updates a customer.
	 * 
	 *   PUT http://XXXX/customers/{id}
	 * 
	 * @param id       Customer id will get parsed from URI path
	 * @param customer Customer object will get created and injected from request body
	 * @return         Response with status 200 (OK) and updated customer object.
	 * 
	 */
	/**
	 * 
	 * @param id
	 * @param customer
	 * @return
	 */
	@PUT
	@Path("{id}")
	public Response updateCustomer(@PathParam("id") Integer id, Customer customer) {
		customer.setId(id);
		LOGGER.info("Customer to update: " + customer);
		Customer updated = dao.updateCustomer(customer);
		return Response.ok(updated).build();
	}
	
	/**
	 * Removes a customer.
	 * 
	 *   DELETE http://XXXX/customers/{id}
	 * 
	 * @return Response object with status 200 (OK) and deleted customer object.
	 * 
	 */
	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") Integer id) {
		Customer customer = new Customer(id,null,null);
		LOGGER.info("Customer to delete: " + customer);
		Customer deleted = dao.deleteCustomer(customer);
		return Response.ok(deleted).build();
	}
	
	
	/**
	 * Gets all accounts by owner id
	 * 
	 *   GET http://XXXX/customer/{id}/accounts
	 *   
	 * @return Response object with a collection of found accounts.
	 */
	@GET
	@Path("{id}/accounts")
	public Response getAccountsById(@PathParam("id") Integer ownerId) {
		Collection<Account> accounts = dao.findAccounts(new Customer(ownerId,null,null));
		return Response.ok(accounts).build();
	}
	
}
