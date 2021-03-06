package com.example.finallabassignment.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finallabassignment.model.Person;


@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "persons";
    private static AppDatabase Instance;

    public static AppDatabase getInstance(Context context) {
        if (Instance == null) {
            synchronized (LOCK) {
                Instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return Instance;
    }

    public abstract PersonDao personDao();
}
