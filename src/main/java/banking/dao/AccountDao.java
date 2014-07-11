package banking.dao;

import java.util.Collection;

import banking.model.Account;
import banking.model.Customer;

public interface AccountDao {

	Account saveAccount(Account account);
	Account updateAccount(Account account);
	Account deleteAccount(Account account);

	Account findAccountById(Integer id);
	Collection<Account> findAccounts();
	Collection<Account> findAccountsByCustomer(Customer customer);
		
}
