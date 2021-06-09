package com.group6.noteproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Account implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id; // Account id

    @ColumnInfo(name="username")
    private String username; // Account username

    @ColumnInfo(name="password")
    private String password; // Account password

    public Account() {
    }

    @Ignore
    public Account(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

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
