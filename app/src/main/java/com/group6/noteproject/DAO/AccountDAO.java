package com.group6.noteproject.DAO;

import android.accounts.Account;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface AccountDAO {

    @Query("SELECT * FROM account WHERE username = :username AND password = :password")
    public Account logIn(String username, String password);

    @Query("SELECT * FROM account WHERE username = :username")
    public Account getUserByUsername(String username);
}
