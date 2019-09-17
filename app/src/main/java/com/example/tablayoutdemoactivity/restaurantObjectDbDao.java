package com.example.tablayoutdemoactivity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface restaurantObjectDbDao {

    @Query("SELECT * FROM RestaurantObjectDb")
    List<RestaurantObjectDb> getAll();

    @Query("SELECT * FROM RestaurantObjectDb WHERE Favourites =1")
    LiveData<RestaurantObjectDb> getFavs();


  /*  @Query("SELECT `Restaurant Names`,AggregateRating,Cuisines FROM RestaurantObjectDb")
    List<RestaurantObjectDb> getName();
*/
    /*@Query("DELETE FROM RestaurantObjectDb")
    void deleteAll(RestaurantObjectDb restaurantObjectDb);*/

    @Query("SELECT COUNT(*) FROM RestaurantObjectDb")
    int getCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RestaurantObjectDb restaurantObjectDb);


    @Delete
    void delete(RestaurantObjectDb restaurantObjectDb);

    @Update
    void update(RestaurantObjectDb restaurantObjectDb);

    @Query("SELECT * FROM RestaurantObjectDb")
    LiveData<List<RestaurantObjectDb>> getAllRest();
}
