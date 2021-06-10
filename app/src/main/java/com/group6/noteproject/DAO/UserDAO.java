package com.group6.noteproject.DAO;

import androidx.room.Dao;
import androidx.room.Insert;

import com.group6.noteproject.model.User;

@Dao
public interface UserDAO {

    @Insert
    long insertUser(User user);
}
