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

    private NoteService noteService;    // note service
    private UserService userService;    // user service
    private Account account;            // account service
    private int noteId;                 // note ID
    private int noteUserId;             // user ID (who has the note)
    private Intent passedIntent;        // the passed intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Service instantiation */
        noteService = new NoteService(this);
        userService = new UserService(this);

        setContentView(R.layout.activity_view_note);

        passedIntent = getIntent();     // get the passed intent

        /* Set the account object according to passed intent */
        if (passedIntent.hasExtra(Constraint.ACCOUNT_KEY)){
            account = (Account) passedIntent.getSerializableExtra(Constraint.ACCOUNT_KEY);
        } else {
            account = userService.getAccountById(passedIntent.getIntExtra("noteUserId", -1));
        }

        /* Get extras from intent if there are any */
        noteId = passedIntent.getIntExtra("noteId", -1);
        noteUserId = passedIntent.getIntExtra("noteUserId", -1);
        String noteTitle = passedIntent.getStringExtra("noteTitle");
        String noteContent = passedIntent.getStringExtra("noteContent");

        /* Get text fields by ID and fill them if there are data */
        EditText etNoteTitle = findViewById(R.id.et_note_title);
        EditText etNoteContent = findViewById(R.id.et_note_content);
        etNoteTitle.setText(noteTitle, TextView.BufferType.EDITABLE);
        etNoteContent.setText(noteContent, TextView.BufferType.EDITABLE);
    }

    /**
     * Save floating button action
     *
     * @param v view
     */
    public void onSave(View v) {
        /* Get text fields by ID */
        EditText txtNoteTitle = findViewById(R.id.et_note_title);
        EditText txtNoteContent = findViewById(R.id.et_note_content);

        /* Creates new note if note doesn't exist, or update note if note exists */
        if (!passedIntent.hasExtra("noteId") && !passedIntent.hasExtra("noteUserId")) {
            /* Create new note and set its info */
            Note note = new Note();
            note.setUserId(account.getId());
            note.setTitle(txtNoteTitle.getText().toString());
            note.setContent(txtNoteContent.getText().toString());
            note.setDeleted(false);

            // if insert successfully, show message and go back to note list, else only show message
            if (noteService.insertNote(note)) {
                Toast.makeText(this, "Note saved!!!", Toast.LENGTH_SHORT).show();
                goHome();
            } else {
                Toast.makeText(this, "Save note failed!!!", Toast.LENGTH_SHORT).show();
            }
        } else {
            /* Get note and update note info */
            Note note = noteService.getNoteById(noteId);
            note.setTitle(txtNoteTitle.getText().toString());
            note.setContent(txtNoteContent.getText().toString());

            // shows message if note is updated sucessfully or not
            if (noteService.updateNote(note)){
                Toast.makeText(this, "Note updated!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Update note failed!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Custom on back pressed action navigate to home
     */
    @Override
    public void onBackPressed() {
        goHome();
    }

    /**
     * Go back to main activity
     *
     * @param v view
     */
    public void backToHome(View v) {
        goHome();
    }

    /**
     * Go back to main activity
     */
    public void goHome(){
        /* Go back to main activity */
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constraint.ACCOUNT_KEY, account);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
