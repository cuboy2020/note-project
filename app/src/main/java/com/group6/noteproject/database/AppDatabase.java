package com.group6.noteproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.group6.noteproject.DAO.AccountDAO;
import com.group6.noteproject.DAO.NoteDAO;
import com.group6.noteproject.DAO.UserDAO;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.Note;
import com.group6.noteproject.model.User;
import com.group6.noteproject.util.Constraint;

@Database(entities = {Account.class, Note.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase dbInstance;

    public static AppDatabase getInstance(final Context context) {
        if (dbInstance == null) {
            dbInstance =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            Constraint.DATABASE_NAME).allowMainThreadQueries().build();
        }
        return dbInstance;
    }

    private static void destroyInstance(){
        dbInstance = null;
    }

    public abstract AccountDAO accountDAO();

    public abstract NoteDAO noteDAO();

    public abstract UserDAO userDAO();
}
