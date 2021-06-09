package com.group6.noteproject.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AccountAndUser {
    @Embedded
    public Account account;

    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public User user;

}
