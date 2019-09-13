package com.example.tablayoutdemoactivity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class restaurantObjectDb implements Serializable {

    @PrimaryKey(autoGenerate = false)
    private int id;

    @ColumnInfo(name = "Restaurant Names")
    private String name;

    @ColumnInfo(name = "Cuisines")
    private String cuisines;

    @ColumnInfo(name = "Cost for Two")
    private int average_cost_for_two;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public int getAverage_cost_for_two() {
        return average_cost_for_two;
    }

    public void setAverage_cost_for_two(int average_cost_for_two) {
        this.average_cost_for_two = average_cost_for_two;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public user_rating getUserRating() {
        return userRating;
    }

    public void setUserRating(user_rating userRating) {
        this.userRating = userRating;
    }

    public location getRestLocation() {
        return restLocation;
    }

    public void setRestLocation(location restLocation) {
        this.restLocation = restLocation;
    }

    @ColumnInfo(name = "Thumb URL")
    private String thumb;

    @ColumnInfo(name = "User Rating")
    private user_rating userRating;

    @ColumnInfo(name = "Restaurant Location")
    private location restLocation;




}
