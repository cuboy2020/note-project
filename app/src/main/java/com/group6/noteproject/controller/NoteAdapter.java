package com.group6.noteproject.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.noteproject.R;
import com.group6.noteproject.model.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    /**
     * Class represents each recycler view's item view in note list
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;      // note title
        private TextView txtContent;    // note content

        /**
         * Constructor
         * @param itemView item view
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tv_note_item_title);
            txtContent = itemView.findViewById(R.id.tv_note_item_content);
        }
    }

    private Context context; // activity context
    private List<Note> notes; // list of notes

    /**
     * Constructor
     * @param context activity context
     * @param notes list of notes
     */
    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    /**
     * Create new view holder when there are no existing ones that can be reused
     * @param parent view group
     * @param viewType view type
     * @return the view holder
     */
    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.item_note, parent, false);
        return new ViewHolder(noteView);
    }

    /**
     * Display items of the adatper
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder,
                                 int position) {
        Note note = notes.get(position);                // get note from position
        holder.txtTitle.setText(note.getTitle());       // set note title
        holder.txtContent.setText(note.getContent());   // set note content
    }

    /**
     * Get amount of notes in list
     * @return the amount of notes in list
     */
    @Override
    public int getItemCount() {
        return notes.size();
    }

    /**
     * Get note's ID from its position in note list
     * @param position position of note in note list
     * @return the note's ID
     */
    public int getNoteId(int position){
        return notes.get(position).getId();
    }

    /**
     * Return note list which the adapter uses
     * @return list of notes
     */
    public List<Note> getNotes() {
        return notes;
    }
}
