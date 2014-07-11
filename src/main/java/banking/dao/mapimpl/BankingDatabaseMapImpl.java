package banking.dao.mapimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import banking.model.Account;
import banking.model.Customer;

public class BankingDatabaseMapImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BankingDatabaseMapImpl.class);

	private static AtomicInteger customerKeys = new AtomicInteger(0);
	private static AtomicInteger accountKeys = new AtomicInteger(0);
	
	private static Map<Integer,Customer> customers = new HashMap<>();
	private static Map<Integer,Account> accounts = new HashMap<>();

	static {
		initializeDatabase();
	}
	
	private BankingDatabaseMapImpl() {};
	
	// GET ALL ENTRIES
	public static Collection<Customer> findAllCustomers() {
		Collection<Customer> result = new ArrayList<>();
		for ( Map.Entry<Integer,Customer> entry : customers.entrySet() ) {
			result.add(entry.getValue());
		}
		return result;
	}
	public static Collection<Account> findAllAccounts() {
		Collection<Account> result = new ArrayList<>();
		for ( Map.Entry<Integer,Account> entry : accounts.entrySet() ) {
			result.add(entry.getValue());
		}
		return result;
	}
	
	// GET ENTRY BY ID
	public static Customer findCustomerById(Integer id) {
		if ( id == null ) throw new RuntimeException("Id cannot be null!");
		return customers.get(id);
	}
	public static Account findAccountById(Integer id) {
		if ( id == null ) throw new RuntimeException("Id cannot be null!");
		return accounts.get(id);
	}
	
	// DELETE ENTRY BY ID
	public synchronized static Customer deleteCustomerById(Integer id) {
		if ( id == null ) throw new RuntimeException("Id cannot be null!");
		Customer removed = customers.remove(id);
		if ( removed == null ) throw new RuntimeException("Customer not found, id=" + id);
		List<Integer> accountKeysToRemove = new ArrayList<>();
		for ( Map.Entry<Integer, Account>  entry : accounts.entrySet()) {
			if ( removed.equals(entry.getValue().getOwner()) ) {
				accountKeysToRemove.add(entry.getKey());
			}
		}
		for (Integer key : accountKeysToRemove) {
			accounts.remove(key);
		}
		return removed;
	}
	public synchronized static Account deleteAccountById(Integer id) {
		if ( id == null ) throw new RuntimeException("Id cannot be null!");
		Account removed = accounts.remove(id);
		if ( removed == null ) throw new RuntimeException("Account not found, id=" + id);
		return removed;
	}

	// UPDATE ENTRY
	public synchronized static Customer updateCustomer(Customer customer) {
		if ( customer == null ) throw new RuntimeException("Customer cannot be null!");
		Customer found = customers.get(customer.getId());
		if ( found == null ) throw new RuntimeException("Customer not found: " + customer);
		customers.put(customer.getId(), customer);
		return customer;
	}
	public synchronized static Account updateAccount(Account account) {
		if ( account == null ) throw new RuntimeException("Account cannot be null!");
		Account found = accounts.get(account.getId());
		if ( found == null ) throw new RuntimeException("Account not found: " + account);
		accounts.put(account.getId(), account);
		return account;
	}
	
	// SAVE ENTRY
	public synchronized static Customer saveCustomer(Customer customer) {
		if ( customer == null ) throw new RuntimeException("Customer cannot be null!");
		if ( customer.getId() != null ) throw new RuntimeException("Customer id needs to be null, database generates keys dynamically.");
		customer.setId(customerKeys.incrementAndGet());
		customers.put(customer.getId(), customer);
		return customer;
	}
	public synchronized static Account saveAccount(Account account) {
		if ( account == null ) throw new RuntimeException("Account cannot be null!");
		if ( account.getId() != null ) throw new RuntimeException("Account id needs to be null, database generates keys dynamically.");
		account.setId(accountKeys.incrementAndGet());
		accounts.put(account.getId(), account);
		return account;
	}
	
	public synchronized static void clearDatabase() {
		LOGGER.info("Clearing database.");
		customers = new HashMap<>();
		accounts = new HashMap<>();
		customerKeys = new AtomicInteger(0);
		accountKeys = new AtomicInteger(0);
	}
	
	public synchronized static void initializeDatabase() {
		
		clearDatabase();
		
		LOGGER.info("Initializing database.");		
		
		saveCustomer(new Customer(null,"John Doe", "john@email.com"));
		saveCustomer(new Customer(null,"Jane Doe", "jane@email.com"));
		saveCustomer(new Customer(null,"Jack Doe", "jack@email.com"));
		saveCustomer(new Customer(null,"Jill Doe", "jill@email.com"));

		saveAccount(new Account(null,findCustomerById(1),100.0,"John Account 1"));
		saveAccount(new Account(null,findCustomerById(1),200.0,"John Account 2"));
		saveAccount(new Account(null,findCustomerById(2),300.0,"Jane Account 1"));
		saveAccount(new Account(null,findCustomerById(3),400.0,"Jack Account 1"));
		saveAccount(new Account(null,findCustomerById(4),500.0,"Jill Account 1"));

	}
	
}
