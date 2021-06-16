package com.group6.noteproject.service;

import android.content.Context;

import com.group6.noteproject.DAO.NoteDAO;
import com.group6.noteproject.database.AppDatabase;
import com.group6.noteproject.model.Note;

import java.util.List;

public class NoteService {

    AppDatabase appDatabase;
    NoteDAO noteDAO;

    public NoteService(Context context) {
        appDatabase = AppDatabase.getInstance(context);
        this.noteDAO = appDatabase.noteDAO();
    }

    /**
     * Get all notes of user
     * @param userId id of user
     * @return list of notes of user
     */
    public List<Note> getNotesByUserId(int userId){
        return noteDAO.getNotesByUserId(userId);
    }

    /**
     * Get specific note
     * @param id id of note
     * @return Note object
     */
    public Note getNoteById(int id){
        return noteDAO.getNoteById(id);
    }

    /**
     * Insert new note
     * @param note note object
     * @return insert success status
     */
    public boolean insertNote(Note note){
        if(note.getTitle().isEmpty() || note.getTitle().equalsIgnoreCase("")){
            note.setTitle("untitled");
        }
        return noteDAO.insertNote(note) > 0;
    }

    /**
     * Update note
     * @param note updated note
     * @return update success status
     */
    public boolean updateNote(Note note){
        if(note.getTitle().isEmpty() || note.getTitle().equalsIgnoreCase("")){
            note.setTitle("untitled");
        }
        return noteDAO.updateNote(note) > 0;
    }

    /**
     * Delete note
     * @param note note want to delete
     * @return delete success status
     */
    public boolean deleteNote(Note note){
        return noteDAO.deleteNote(note) > 0;
    }

}
