package com.example.tablayoutdemoactivity;

public class restaurantObject {

    int id;
    boolean fav;
    String name;
    String cuisines;
    int average_cost_for_two;
    String thumb;               //url for thumbnail
    int aggregate_rating;
    int votes;
    String address;
    String city;
    double latitude;
    double longitude;

    public restaurantObject
            (int id,boolean fav,String name,String cuisines,
             int average_cost_for_two,String thumb,int aggregate_rating,int votes,
             String address,String city,double latitude,double longitude){

        this.id = id;
        this.fav = fav;
        this.name =  name;
        this.cuisines = cuisines;
        this.average_cost_for_two = average_cost_for_two;
        this.thumb = thumb;
        this.aggregate_rating = aggregate_rating;
        this.votes = votes;
        this.address=address;
        this.city=city;
        this.latitude=latitude;
        this.longitude=longitude;

    }
    public restaurantObject(){}
    public restaurantObject(String name){this.name = name;}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
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






}
