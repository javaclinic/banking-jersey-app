package banking.rest.resources;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import banking.dao.CustomerDao;
import banking.dao.mapimpl.CustomerDaoMapImpl;
import banking.model.Customer;

//LABTASK: Use appropriate Path to map this resource
public class CustomerResource {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public CustomerResource() {
		LOGGER.info("Constructing CustomerResource object.");
	}
	
	private CustomerDao dao = new CustomerDaoMapImpl();

	// LABTASK: Use appropriate Path and methods for this task
	public Response getCustomers() {
		// LABTASK: Use dao to find all customers
		// LABTASK: Build appropriate response and return back
		return null;
	}

	// LABTASK: Use appropriate Path and methods for this task
	// LABTASK: Insert @PathParam so you can inject appropriate id from the path
	public Response getCustomer(Integer id) {
		// LABTASK: use dao to find customer
		// LABTASK: Throw an exception if the customer was not found, return approrpirate response NOT_FOUND
		// LABTASK: Build appropriate response and return back
		return null;
	}
	
	// LABTASK: Use appropriate Path and methods for this task
	public Response saveCustomer(Customer customer) {
		// LABTASK: use dao to save customer
		// LABTASK: Build appropriate response and return back
		return null;
	}

	// LABTASK: Use appropriate Path and methods for this task
	// LABTASK: Insert @PathParam so you can inject appropriate id from the path
	public Response updateCustomer(Integer id, Customer customer) {
		// LABTASK: set the customer id from the path
		// LABTASK: use dao to update the customer
		// LABTASK: Build appropriate response and return back
		return null;
	}
	
	// LABTASK: Use appropriate Path and methods for this task
	// LABTASK: Insert @PathParam so you can inject appropriate id from the path
	public Response deleteCustomer(Integer id) {
		// LABTASK: create empty customer with id
		// LABTASK: use dao to delete customer with id
		// LABTASK: Build appropriate response and return back
		return null;
	}
	
}
