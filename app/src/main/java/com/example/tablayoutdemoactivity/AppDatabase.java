package com.example.tablayoutdemoactivity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities =
        {restaurantObjectDb.class,
                location_db.class,
                user_rating_db.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract restaurantObjectDbDao restaurantObjectDbDao();
}
