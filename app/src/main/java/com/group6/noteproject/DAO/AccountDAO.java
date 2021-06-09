package com.group6.noteproject.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.group6.noteproject.model.Account;

@Dao
public interface AccountDAO {

    @Query("SELECT * FROM account WHERE username = :username AND password = :password")
    Account logIn(String username, String password);

    @Query("SELECT * FROM account WHERE username = :username")
    Account getAccountByUsername(String username);

    @Insert
    long insertAccount(Account account);
}
