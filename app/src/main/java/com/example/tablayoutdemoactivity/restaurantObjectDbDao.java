package com.example.tablayoutdemoactivity;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface restaurantObjectDbDao {

    @Query("SELECT * FROM restaurantObjectDb")
    List<restaurantObjectDb> getAll();

    @Insert
    void insert(restaurantObjectDb restaurantObjectDb);

    @Delete
    void delete(restaurantObjectDb restaurantObjectDb);

    @Update
    void update(restaurantObjectDb restaurantObjectDb);

}
