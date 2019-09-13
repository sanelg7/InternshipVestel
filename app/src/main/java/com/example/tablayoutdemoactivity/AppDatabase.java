package com.example.tablayoutdemoactivity;
import androidx.annotation.*;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities =
        {RestaurantObjectDb.class,},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract restaurantObjectDbDao restaurantObjectDbDao();
}
