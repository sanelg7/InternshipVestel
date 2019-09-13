package com.example.tablayoutdemoactivity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface restaurantObjectDbDao {

    @Query("SELECT * FROM RestaurantObjectDb")
    List<RestaurantObjectDb> getAll();

    @Insert
    void insert(RestaurantObjectDb restaurantObjectDb);

    @Delete
    void delete(RestaurantObjectDb restaurantObjectDb);

    @Update
    void update(RestaurantObjectDb restaurantObjectDb);

}
