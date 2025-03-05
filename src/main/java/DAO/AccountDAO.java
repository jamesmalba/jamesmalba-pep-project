package DAO;

import Util.ConnectionUtil;
import Model.Account;
import java.sql.*;


public class AccountDAO {
    
    /**
    * Creates a new account into the database.
    *
    * @param account The account being added
    * @return The account object with the account id
    */
    public Account insertAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
    * Verifies account logins
    *
    * @param account The account being verified
    * @return The account object with the account id, null otherwise
    */
    public Account verifyAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account accountRs = new Account(rs.getInt("account_id"), 
                            rs.getString("username"), 
                            rs.getString("password"));
                return accountRs;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
    * Checks if the account name is not taken in the database.
    *
    * @param username The username string being checked if exists. 
    * @return false if the username is used, true if username is not taken
    */
    public boolean validAccountUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return false;
            else return true;
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
    * Checks if the account id exists in the database.
    *
    * @param account_id The account being checked if exists. 
    * @return true if the account id is in the database, false otherwise. 
    */
    public boolean validAccountId(int account_id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE account_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return true;
            else return false;
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    
}
