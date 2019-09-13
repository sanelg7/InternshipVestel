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
    public restaurantObject(String name){this.name = name;}




}
