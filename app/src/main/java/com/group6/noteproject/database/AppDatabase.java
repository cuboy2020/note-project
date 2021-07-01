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
import com.group6.noteproject.util.Constant;

@Database(entities = {Account.class, Note.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase dbInstance;  // database instance

    /**
     * Get instance of app database, create if not existed
     *
     * @param context context of activity
     * @return instance of app database
     */
    public static AppDatabase getInstance(final Context context) {
        if (dbInstance == null) {
            dbInstance =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            Constant.DATABASE_NAME).allowMainThreadQueries().build();
        }
        return dbInstance;
    }

    /**
     * Destroy the app database instance
     */
    private static void destroyInstance() {
        dbInstance = null;
    }

    /* Abstract DAO methods */
    public abstract AccountDAO accountDAO();

    public abstract NoteDAO noteDAO();

    public abstract UserDAO userDAO();
}
