package com.example.tablayoutdemoactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class RestaurantObjectDb implements Serializable {

    public String defaultString = "";
    public int defaultInt = 0;
    public boolean defaultBool = false;

    @PrimaryKey(autoGenerate = false)
    private int id;

    @ColumnInfo(name = "Favourites")
    @Nullable private boolean fav;


    @ColumnInfo(name = "Restaurant Names")
    private String name;

    @ColumnInfo(name = "Cuisines")
    private String cuisines;

    @ColumnInfo(name = "Cost for Two")
    private int average_cost_for_two;

    @ColumnInfo(name = "AggregateRating")
    private int aggregate_rating;

    @ColumnInfo(name = "Votes")
    private int votes;

    @ColumnInfo(name = "Address")
    private String address;

    @ColumnInfo(name = "City")
    private String city;

    @ColumnInfo(name = "Lat")
    @NonNull private double latitude;

    @ColumnInfo(name = "Long")
    @NonNull private double longitude;

    @ColumnInfo(name = "Thumb URL")
    private String thumb;

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public int getAggregate_rating() {
        return aggregate_rating;
    }

    public void setAggregate_rating(int aggregate_rating) {
        this.aggregate_rating = aggregate_rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

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


}
