package com.group6.noteproject.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithNotes {
    @Embedded
    public User user;

    @Relation(
            parentColumn = "id",
            entityColumn = "user_id"
    )
    public List<Note> notes;
}
