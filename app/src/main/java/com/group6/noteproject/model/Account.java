package com.group6.noteproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/* Account Entity */
@Entity
public class Account implements Serializable {

    /* Entity Properties */
    @PrimaryKey(autoGenerate = true)
    private int id; // Account's ID

    @ColumnInfo(name="username")
    private String username; // Account's username

    @ColumnInfo(name="password")
    private String password; // Account's password

    /* Constructors */
    public Account() {
    }

    @Ignore
    public Account(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /* Getters and Setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
