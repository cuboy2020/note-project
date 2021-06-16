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
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(this, recyclerView, new RecyclerViewTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                int noteId = noteAdapter.getNoteId(position);
                Note note = noteService.getNoteById(noteId);

                /* Create popup menu and processing logic */
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    /**
                     * Opens popup menu on note click
                     * @param item
                     * @return
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
                                alert.setTitle("Delete")
                                        .setMessage("Are you sure you want to delete this note?")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            /**
                                             * Closes the dialog
                                             * @param dialog
                                             * @param which
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

                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();
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
    }

    /**
     * Logout action
     */
    public void logOut(){
        finish();
    }

    /**
     * Floating add button onclick event
     *
     * @param v
     */
    public void onClick(View v) {
        Intent intent = new Intent(this, ViewEditNoteActivity.class);
        intent.putExtra(Constraint.ACCOUNT_KEY, account);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
