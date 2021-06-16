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

    /**
     * Get all notes of an user by the user's ID
     * @param userId user's ID
     * @return list of notes
     */
    @Query("SELECT * FROM note WHERE user_id = :userId")
    List<Note> getNotesByUserId(int userId);

    /**
     * Get a note by its ID
     * @param id note's ID
     * @return Note object
     */
    @Query("SELECT * FROM note WHERE id = :id")
    Note getNoteById(int id);

    /**
     * Insert a new note into database
     * @param note the note to insert
     * @return inserted note's row ID
     */
    @Insert
    Long insertNote(Note note);

    /**
     * Update an existing note
     * @param note the updated note
     * @return update result
     */
    @Update
    int updateNote(Note note);

    /**
     * Delete a note
     * @param note the note to delete
     * @return delete result
     */
    @Delete
    int deleteNote(Note note);

}
