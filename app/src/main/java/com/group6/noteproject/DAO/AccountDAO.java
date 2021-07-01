package com.group6.noteproject.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.group6.noteproject.model.Account;

@Dao
public interface AccountDAO {

    /**
     * Log an user in
     *
     * @param username account's username
     * @param password account's password
     * @return Account object
     */
    @Query("SELECT * FROM account WHERE username = :username AND password = :password")
    Account logIn(String username, String password);

    /**
     * Get an account by its ID
     *
     * @param accountId account's ID
     * @return Account object
     */
    @Query("SELECT * FROM account WHERE id = :accountId")
    Account getAccountById(int accountId);

    /**
     * Get an account by its username
     *
     * @param username account's username
     * @return Account object
     */
    @Query("SELECT * FROM account WHERE username = :username")
    Account getAccountByUsername(String username);

    /**
     * Insert an account into database
     *
     * @param account the account to insert
     * @return inserted account's row ID
     */
    @Insert
    long insertAccount(Account account);
}
