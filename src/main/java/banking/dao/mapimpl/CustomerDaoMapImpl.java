package banking.dao.mapimpl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import banking.dao.CustomerDao;
import banking.model.Customer;

public class CustomerDaoMapImpl implements CustomerDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public CustomerDaoMapImpl() {
		LOGGER.info("Constructing CustomerDao object.");
	}
	
	@Override
	public Customer saveCustomer(Customer customer) {
		LOGGER.info("Saving customer: " + customer);
		return BankingDatabaseMapImpl.saveCustomer(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		LOGGER.info("Updating customer: " + customer);
		return BankingDatabaseMapImpl.updateCustomer(customer);
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		LOGGER.info("Deleting customer: " + customer);
		if ( customer == null ) throw new RuntimeException("Customer cannot be null!");
		return BankingDatabaseMapImpl.deleteCustomerById(customer.getId());
	}

	@Override
	public Customer findCustomerById(Integer id) {
		LOGGER.info("Finding customer by id: " + id);
		return BankingDatabaseMapImpl.findCustomerById(id);
	}

	@Override
	public Collection<Customer> findCustomers() {
		LOGGER.info("Finding all customers.");
		return BankingDatabaseMapImpl.findAllCustomers();
	}

}
