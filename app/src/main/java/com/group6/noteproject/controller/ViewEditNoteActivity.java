package com.group6.noteproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.noteproject.R;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.Note;
import com.group6.noteproject.service.NoteService;
import com.group6.noteproject.service.UserService;
import com.group6.noteproject.util.Constraint;

public class ViewEditNoteActivity extends AppCompatActivity {

    private NoteService noteService;
    private UserService userService;
    private Account account;
    private int noteId;
    private int noteUserId;
    private Intent passedIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteService = new NoteService(this);
        userService = new UserService(this);

        setContentView(R.layout.activity_view_note);

        passedIntent = getIntent();

        if (passedIntent.hasExtra(Constraint.ACCOUNT_KEY)){
            account = (Account) passedIntent.getSerializableExtra(Constraint.ACCOUNT_KEY);
        } else {
            account = userService.getAccountById(passedIntent.getIntExtra("noteUserId", -1));
        }

        noteId = passedIntent.getIntExtra("noteId", -1);
        noteUserId = passedIntent.getIntExtra("noteUserId", -1);
        String noteTitle = passedIntent.getStringExtra("noteTitle");
        String noteContent = passedIntent.getStringExtra("noteContent");

        EditText etNoteTitle = findViewById(R.id.et_note_title);
        etNoteTitle.setText(noteTitle, TextView.BufferType.EDITABLE);

        EditText etNoteContent = findViewById(R.id.et_note_content);
        etNoteContent.setText(noteContent, TextView.BufferType.EDITABLE);
    }

    /**
     * Save floating button action
     *
     * @param v
     */
    public void onSave(View v) {
        EditText txtNoteTitle = findViewById(R.id.et_note_title);
        EditText txtNoteContent = findViewById(R.id.et_note_content);
        if (!passedIntent.hasExtra("noteId") && !passedIntent.hasExtra("noteUserId")) {
            Note note = new Note();
            note.setUserId(account.getId());
            note.setTitle(txtNoteTitle.getText().toString());
            note.setContent(txtNoteContent.getText().toString());
            note.setDeleted(false);
            if (noteService.insertNote(note)) {
                Toast.makeText(this, "Note saved!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Save note failed!!!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Note note = noteService.getNoteById(noteId);
            note.setTitle(txtNoteTitle.getText().toString());
            note.setContent(txtNoteContent.getText().toString());

            if (noteService.updateNote(note)){
                Toast.makeText(this, "Note updated!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Update note failed!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Go back to main activity
     *
     * @param v
     */
    public void backToHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constraint.ACCOUNT_KEY, account);
        startActivity(intent);
    }
}
