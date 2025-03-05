package Service;

import Model.Account;
import DAO.AccountDAO;
import DAO.MessageDAO;
import Service.AccountService;

import java.util.List;

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
        return accountDAO.insertAccount(account);
    }

    // Verify Account Login
    public Account verifyAccount(Account account) {
        return accountDAO.verifyAccount(account);
    }

    // Get Account by ID
    public Account getAccountById(int account_id) {
        return accountDAO.getAccountById(account_id);
    }
    
}
