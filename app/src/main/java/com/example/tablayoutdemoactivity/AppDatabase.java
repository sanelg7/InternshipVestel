package com.example.tablayoutdemoactivity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.*;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Database(entities =
        {RestaurantObjectDb.class,},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract restaurantObjectDbDao restaurantObjectDbDao();

    private static volatile AppDatabase appDatabaseInstance;

    static AppDatabase getDatabase(final Context context) {
        if (appDatabaseInstance == null) {
            synchronized (AppDatabase.class) {
                if (appDatabaseInstance == null) {
                    appDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database")
                            .build();
                }
            }
        }
        return appDatabaseInstance;
    }

}