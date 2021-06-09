package com.group6.noteproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @PrimaryKey
    private int id; // User ID

    @ColumnInfo
    private String fullname; // user fullname

    @ColumnInfo
    private String email;  // user email

    @ColumnInfo
    private String address; // user address

    @ColumnInfo
    private String phone; // user phone

    @ColumnInfo
    private String birthdate; // user birthdate

    public User() {
    }

    @Ignore
    public User(int id,
                String fullname,
                String email,
                String address,
                String phone,
                String birthdate) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
