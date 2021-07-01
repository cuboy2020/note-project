package com.group6.noteproject.service;


import android.content.Context;

import com.group6.noteproject.DAO.AccountDAO;
import com.group6.noteproject.DAO.UserDAO;
import com.group6.noteproject.database.AppDatabase;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.User;

public class UserService {

    private AppDatabase appDatabase;    // note database
    private UserDAO userDAO;            // user DAO
    private AccountDAO accountDAO;      // account DAO

    /**
     * Constructor
     *
     * @param context context of activity
     */
    public UserService(Context context) {
        appDatabase = AppDatabase.getInstance(context);
        this.userDAO = appDatabase.userDAO();
        this.accountDAO = appDatabase.accountDAO();
    }

    /**
     * Login method
     *
     * @param username input username
     * @param password input password
     * @return Account object if credentials is valid
     */
    public Account logIn(String username, String password) {
        Account account = accountDAO.logIn(username, password);
        return account;
    }

    /**
     * Register new user
     *
     * @param account contain username and password
     * @param user    user information
     * @return success status
     */
    public boolean register(Account account, User user) {
        if (accountDAO.getAccountByUsername(account.getUsername()) != null) {
            return false;
        }

        return accountDAO.insertAccount(account) > 0 && userDAO.insertUser(user) > 0;
    }

    /**
     * Get an account by its username
     *
     * @param username username of the account
     * @return Account object
     */
    public Account getAccountByUsername(String username) {
        Account account = accountDAO.getAccountByUsername(username);

        return account;
    }

    /**
     * Get an account by its ID
     *
     * @param id ID of the account
     * @return Account object
     */
    public Account getAccountById(int id) {
        Account account = accountDAO.getAccountById(id);

        return account;
    }
}
