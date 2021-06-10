package com.group6.noteproject.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.group6.noteproject.model.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Query("SELECT * FROM note WHERE user_id = :userId")
    List<Note> getNotesByUserId(int userId);

    @Query("SELECT * FROM note WHERE id = :id")
    Note getNoteById(int id);

    @Insert
    Long insertNote(Note note);

    @Update
    int updateNote(Note note);

    @Delete
    int deleteNote(Note note);

}
