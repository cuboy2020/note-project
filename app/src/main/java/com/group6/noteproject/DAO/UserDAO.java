package com.group6.noteproject.DAO;

import androidx.room.Dao;
import androidx.room.Insert;

import com.group6.noteproject.model.User;

@Dao
public interface UserDAO {

    /**
     * Insert a user into database
     *
     * @param user user to insert
     * @return inserted user's row ID
     */
    @Insert
    long insertUser(User user);
}
