package com.group6.noteproject.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.group6.noteproject.model.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Query("SELECT * FROM note WHERE user_id = :userId")
    List<Note> getNotesByUserId(int userId);

    @Query("SELECT * FROM note WHERE id = :id")
    Note getNoteById(int id);
}
