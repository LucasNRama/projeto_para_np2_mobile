package com.example.diariodehumor;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 2, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static volatile NoteDatabase INSTANCE;

    public abstract NoteDao noteDao();

    public static NoteDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    NoteDatabase.class, "note_database")
                            .fallbackToDestructiveMigration()  // Esse método recria o banco de dados se a versão mudar
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


