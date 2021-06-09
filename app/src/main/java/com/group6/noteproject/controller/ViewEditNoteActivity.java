package com.group6.noteproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.noteproject.R;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.Note;
import com.group6.noteproject.service.NoteService;
import com.group6.noteproject.util.Constraint;

public class ViewEditNoteActivity extends AppCompatActivity {

    private NoteService noteService;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteService = new NoteService(this);
        setContentView(R.layout.activity_view_note);

        account = (Account) getIntent().getSerializableExtra(Constraint.ACCOUNT_KEY);
    }

    /**
     * Save floating button action
     * @param v
     */
    public void onSave(View v){
        EditText txtNoteTitle = findViewById(R.id.et_note_content);
        EditText txtNoteContent = findViewById(R.id.et_note_content);
        Note note = new Note();
        note.setUserId(account.getId());
        note.setContent(txtNoteContent.getText().toString());
        note.setDeleted(false);
        note.setTitle(txtNoteTitle.getText().toString());
        if(noteService.insertNote(note)){
            Toast.makeText(this, "Note saved!!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Note save unsuccessful!!!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Go back to main activity
     * @param v
     */
    public void backToHome(View v){
        Intent intent = new Intent(this ,MainActivity.class);
        intent.putExtra(Constraint.ACCOUNT_KEY, account);
        startActivity(intent);
    }
}
