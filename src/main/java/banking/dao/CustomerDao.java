package banking.dao;

import java.util.Collection;

import banking.model.Account;
import banking.model.Customer;

public interface CustomerDao {

	Customer saveCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	Customer deleteCustomer(Customer customer);

	Customer findCustomerById(Integer id);
	Collection<Customer> findCustomers();
	Collection<Account> findAccounts(Customer customer);
		
}
