package com.group6.noteproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.noteproject.R;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.Note;
import com.group6.noteproject.service.NoteService;
import com.group6.noteproject.util.Constraint;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteService noteService;
    private Account account;
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteService = new NoteService(this);
        account = (Account) getIntent().getSerializableExtra(Constraint.ACCOUNT_KEY);
        notes = noteService.getNotesByUserId(account.getId());

        recyclerView = findViewById(R.id.rv_notes);

        noteAdapter = new NoteAdapter(this, notes);


        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    /**
     * Floating add button onclick event
     * @param v
     */
    public void onClick(View v){
        Intent intent = new Intent(this ,ViewEditNoteActivity.class);
        intent.putExtra(Constraint.ACCOUNT_KEY, account);
        startActivity(intent);
    }
}
