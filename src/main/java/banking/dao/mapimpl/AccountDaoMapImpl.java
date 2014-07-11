package banking.dao.mapimpl;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import banking.dao.AccountDao;
import banking.model.Account;
import banking.model.Customer;

public class AccountDaoMapImpl implements AccountDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public AccountDaoMapImpl() {
		LOGGER.info("Constructing AccountDao object.");
	}
	
	@Override
	public Account saveAccount(Account account) {
		LOGGER.info("Saving account:" + account);
		return BankingDatabaseMapImpl.saveAccount(account);
	}

	@Override
	public Account updateAccount(Account account) {
		LOGGER.info("Updating account: " + account);
		return BankingDatabaseMapImpl.updateAccount(account);
	}

	@Override
	public Account deleteAccount(Account account) {
		LOGGER.info("Deleting account: " + account);
		if ( account == null ) throw new RuntimeException("Account cannot be null!");
		return BankingDatabaseMapImpl.deleteAccountById(account.getId());
	}

	@Override
	public Account findAccountById(Integer id) {
		LOGGER.info("Finding an account with id: " + id);
		return BankingDatabaseMapImpl.findAccountById(id);
	}

	@Override
	public Collection<Account> findAccounts() {
		LOGGER.info("Finding all accounts.");
		return BankingDatabaseMapImpl.findAllAccounts();
	}

	@Override
	public Collection<Account> findAccountsByCustomer(Customer customer) {
		LOGGER.info("Finding all accounts by customer: " + customer);
		Collection<Account> result = new ArrayList<>();
		for ( Account account : BankingDatabaseMapImpl.findAllAccounts()) {
			if ( customer.equals(account.getOwner())) result.add(account);
		}
		return null;
	}

}
