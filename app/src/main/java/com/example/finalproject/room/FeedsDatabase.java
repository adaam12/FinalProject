package com.example.finalproject.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finalproject.model.FeedModel;


@Database(entities = {FeedModel.class}, version = 1, exportSchema = false)
public abstract class FeedsDatabase extends RoomDatabase {
    public abstract Dao feedDao();

    private static volatile FeedsDatabase INSTANCE;

    public static FeedsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FeedsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FeedsDatabase.class, "contact_database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
