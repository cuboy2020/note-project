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
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtContent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tv_note_item_title);
            txtContent = itemView.findViewById(R.id.tv_note_item_content);
        }
    }

    private Context context; // Context
    private List<Note> notes; // teachers list

    // Constructors
    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.item_note, parent, false);
        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder,
                                 int position) {
        // Get teacher position
        Note note = notes.get(position);
        // Set teacher's name
        holder.txtContent.setText(note.getContent());
        holder.txtTitle.setText(note.getTitle());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

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
