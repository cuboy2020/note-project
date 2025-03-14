package com.group6.noteproject.service;

import android.content.Context;

import com.group6.noteproject.DAO.NoteDAO;
import com.group6.noteproject.database.AppDatabase;
import com.group6.noteproject.model.Note;

import java.util.List;

public class NoteService {

    private AppDatabase appDatabase;    // note database
    private NoteDAO noteDAO;            // note DAO

    /**
     * Constructor
     *
     * @param context context of activity
     */
    public NoteService(Context context) {
        appDatabase = AppDatabase.getInstance(context);
        this.noteDAO = appDatabase.noteDAO();
    }

    /**
     * Get all notes of user
     *
     * @param userId id of user
     * @return list of notes of user
     */
    public List<Note> getNotesByUserId(int userId) {
        return noteDAO.getNotesByUserId(userId);
    }

    /**
     * Get specific note
     *
     * @param id id of note
     * @return Note object
     */
    public Note getNoteById(int id) {
        return noteDAO.getNoteById(id);
    }

    /**
     * Add/insert new note
     *
     * @param note the note to insert
     * @return insert result
     */
    public boolean insertNote(Note note) {
        // set the title to "Untitled" if note does not have a title
        if (note.getTitle().isEmpty() || note.getTitle().equalsIgnoreCase("")) {
            note.setTitle("Untitled");
        }

        return noteDAO.insertNote(note) > 0;
    }

    /**
     * Update note
     *
     * @param note the updated note
     * @return update result
     */
    public boolean updateNote(Note note) {
        // set the title to "Untitled" if note does not have a title
        if (note.getTitle().isEmpty() || note.getTitle().equalsIgnoreCase("")) {
            note.setTitle("Untitled");
        }

        return noteDAO.updateNote(note) > 0;
    }

    /**
     * Delete note
     *
     * @param note note to delete
     * @return delete result
     */
    public boolean deleteNote(Note note) {
        return noteDAO.deleteNote(note) > 0;
    }

}
