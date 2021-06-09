package com.group6.noteproject.service;


import android.content.Context;

import com.group6.noteproject.DAO.AccountDAO;
import com.group6.noteproject.DAO.UserDAO;
import com.group6.noteproject.database.AppDatabase;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.User;

public class UserService {

    AppDatabase appDatabase;
    UserDAO userDAO;
    AccountDAO accountDAO;

    public UserService(Context context) {
        appDatabase = AppDatabase.getInstance(context);
        this.userDAO = appDatabase.userDAO();
        this.accountDAO = appDatabase.accountDAO();
    }

    /**
     * Login method
     * @param username input username
     * @param password input password
     * @return if credentials is valid
     */
    public Account logIn(String username, String password){
        Account account = accountDAO.logIn(username, password);
        return account;
    }

    /**
     * Register new user
     * @param account contain username and password
     * @param user user information
     * @return success status
     */
    public boolean register(Account account, User user){
        if(accountDAO.getAccountByUsername(account.getUsername()) != null){
            return false;
        }

        return accountDAO.insertAccount(account) > 0 && userDAO.insertUser(user) > 0;
    }
}
