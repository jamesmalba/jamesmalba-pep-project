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

    // Process new Account Registration
    public Account addNewAccount(Account account) {
        if (account.getUsername() == null || account.getPassword().length() < 4) return null;
        if (!accountDAO.validAccountUsername(account.getUsername())) return null;
        return accountDAO.insertAccount(account);
    }

    // Verify Account Login
    public Account verifyAccount(Account account) {
        return accountDAO.verifyAccount(account);
    }

    // Check if account id exists
    public boolean validAccountId(int account_id) {
        return accountDAO.validAccountId(account_id);
    }
    
}
