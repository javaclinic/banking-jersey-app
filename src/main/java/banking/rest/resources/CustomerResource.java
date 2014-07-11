package banking.rest.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import banking.dao.CustomerDao;
import banking.dao.mapimpl.CustomerDaoMapImpl;
import banking.model.Customer;

@Path("customers")
public class CustomerResource {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public CustomerResource() {
		LOGGER.info("Constructing CustomerResource object.");
	}
	
	private CustomerDao dao = new CustomerDaoMapImpl();

	@GET
	public Response getCustomers() {
		return Response.ok(dao.findCustomers()).build();
	}

	@GET
	@Path("{id}")
	public Response getCustomer(@PathParam("id") Integer id) {
		Customer customer = dao.findCustomerById(id);
		if ( customer == null ) return Response.status(Status.NOT_FOUND).build();
		return Response.ok(dao.findCustomerById(id)).build();
	}
	
	@POST
	public Response saveCustomer(Customer customer) {
		LOGGER.info("Customer to save: " + customer);
		Customer saved = dao.saveCustomer(customer);
		return Response.ok(saved).build();
	}

	@PUT
	@Path("{id}")
	public Response updateCustomer(@PathParam("id") Integer id, Customer customer) {
		customer.setId(id);
		LOGGER.info("Customer to update: " + customer);
		Customer updated = dao.updateCustomer(customer);
		return Response.ok(updated).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") Integer id) {
		Customer customer = new Customer(id,null,null);
		LOGGER.info("Customer to delete: " + customer);
		Customer deleted = dao.deleteCustomer(customer);
		return Response.ok(deleted).build();
	}
	
}
