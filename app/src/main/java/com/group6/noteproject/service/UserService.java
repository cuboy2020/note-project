package com.group6.noteproject.service;

import android.accounts.Account;
import android.content.Context;

import com.group6.noteproject.DAO.AccountDAO;
import com.group6.noteproject.DAO.UserDAO;
import com.group6.noteproject.database.AppDatabase;

public class UserService {

    AppDatabase appDatabase;
    UserDAO userDAO;
    AccountDAO accountDAO;

    public UserService(Context context) {
        appDatabase = AppDatabase.getInstance(context);
        this.userDAO = appDatabase.userDAO();
        this.accountDAO = appDatabase.accountDAO();
    }

    public boolean logIn(String username, String password){
        Account account = accountDAO.logIn(username, password);
        return account != null;
    }
}
