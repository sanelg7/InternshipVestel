package com.example.tablayoutdemoactivity;

public class restaurantObject {

    int id;
    String name;
    String cuisines;
    int average_cost_for_two;
    String thumb;               //url for thumbnail
    user_rating userRating;
    location restLocation;

    public restaurantObject(int id,String name,String cuisines,int average_cost_for_two,String thumb,user_rating userRating,location restLocation){

        this.id = id;
        this.name =  name;
        this.cuisines = cuisines;
        this.average_cost_for_two = average_cost_for_two;
        this.thumb = thumb;
        this.userRating = userRating;
        this.restLocation = restLocation;

    }
    public restaurantObject(String name){this.name = name;}




}
