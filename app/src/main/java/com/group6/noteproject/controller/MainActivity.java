package com.group6.noteproject.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.noteproject.R;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.Note;
import com.group6.noteproject.service.NoteService;
import com.group6.noteproject.util.Constraint;
import com.group6.noteproject.view.RecyclerViewTouchListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteService noteService;        // note service
    private Account account;                // user's account object
    private RecyclerView recyclerView;      // recycler view object
    private NoteAdapter noteAdapter;        // note adapter
    private List<Note> notes;               // list of notes of user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteService = new NoteService(this);                                     // instantiate note service
        account = (Account) getIntent().getSerializableExtra(Constraint.ACCOUNT_KEY);   // user's account
        notes = noteService.getNotesByUserId(account.getId());                          // get user's note list

        recyclerView = findViewById(R.id.rv_notes);                 // get the recycler view by ID

        noteAdapter = new NoteAdapter(this, notes);          // instantiate note adapter

        recyclerView.setAdapter(noteAdapter);                       // set note adapter to recycler view
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));                   // set horizontal bar to separate notes in list
        recyclerView.setLayoutManager(new LinearLayoutManager(this));           // set layout

        /* Recycler View's item OnClick Listener */
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(this, recyclerView, new RecyclerViewTouchListener.ClickListener() {
            /**
             * Open popup menu to edit or delete note
             * @param view view
             * @param position note position in list
             */
            @Override
            public void onClick(View view, int position) {
                int noteId = noteAdapter.getNoteId(position);       // get note ID by position
                Note note = noteService.getNoteById(noteId);        // get note by note ID

                /* Create popup menu and processing logic */
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    /**
                     * Opens popup menu on note click
                     * @param item menu item
                     * @return true if popup menu item is clicked, false otherwise
                     */
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_note_item:
                                /* Create an intent to put note info inside */
                                Intent intent = new Intent(MainActivity.this, ViewEditNoteActivity.class);
                                intent.putExtra("noteId", noteId);
                                intent.putExtra("noteUserId", note.getUserId());
                                intent.putExtra("noteTitle", note.getTitle());
                                intent.putExtra("noteContent", note.getContent());

                                startActivity(intent);  // start Edit Note Activity

                                return true;

                            case R.id.delete_note_item:
                                /* Show dialog to confirm user action */
                                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                                alert.setTitle("Delete Note");                                      // set dialog title
                                alert.setMessage("Are you sure you want to delete this note?");     // set dialog message

                                // action if confirmed (Yes)
                                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            /**
                                             * Delete note in DB and update adapter's data
                                             * @param dialog
                                             * @param which
                                             */
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                List<Note> notes = noteAdapter.getNotes();

                                                noteService.deleteNote(note);
                                                notes.remove(position);
                                                noteAdapter.notifyItemRemoved(position);
                                            }
                                        });

                                // action if cancelled (No)
                                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            /**
                                             * Closes the dialog
                                             * @param dialog dialog
                                             * @param which which
                                             */
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });

                                alert.show();           // show the alert dialog
                                return true;

                            default:
                                return false;
                        }
                    }
                });

                popupMenu.inflate(R.menu.popup_menu);   // inflate the popup menu
                popupMenu.show();                       // show the popup menu
            }

            @Override
            public void onLongClick(View view, int position) {
                // do nothing
            }
        }));
    }

    /**
     * Disable back button pressed
     */
    @Override
    public void onBackPressed() {
        // do nothing
    }

    /**
     * Logout action
     *
     * @Param v view
     */
    public void logOut(View v){
        finish();
    }

    /**
     * Floating Add button OnClick event
     *
     * @param v view
     */
    public void onClick(View v) {
        /* Create intent to move to View Edit Note Activity to add note */
        Intent intent = new Intent(this, ViewEditNoteActivity.class);
        intent.putExtra(Constraint.ACCOUNT_KEY, account);                                   // set extra
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   // set flags
        startActivity(intent);
    }
}
