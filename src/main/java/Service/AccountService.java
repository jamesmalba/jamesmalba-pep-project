package Service;

import Model.Account;
import DAO.AccountDAO;
import Service.AccountService;

public class AccountService {

    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /**
    * Adds a new account using accountDAO.
    * Checks if account username is not null and if the password length is greater than 4.
    *
    * @param account The account being added
    * @return The account object with the account id, null for invalid account info. 
    */
    public Account addNewAccount(Account account) {
        if (account.getUsername().isEmpty() || account.getPassword().length() < 4) return null;
        if (!accountDAO.validAccountUsername(account.getUsername())) return null;
        return accountDAO.insertAccount(account);
    }

    /**
    * Verifies Account Login information using accountDAO.
    *
    * @param account The account being verified
    * @return The account object if the account is verified. 
    */
    public Account verifyAccount(Account account) {
        return accountDAO.verifyAccount(account);
    }

    // Check if account id exists
    /**
    * Checcks if the account id exists. 
    *
    * @param account_id the id being checked if exists in database. 
    * @return true if exists, false otherwise. 
    */
    public boolean validAccountId(int account_id) {
        return accountDAO.validAccountId(account_id);
    }
    
}
