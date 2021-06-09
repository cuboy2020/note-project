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
}
