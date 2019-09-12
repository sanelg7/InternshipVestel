package com.example.tablayoutdemoactivity;

public class restaurantObject {

    String name;
    String cuisines;
    int average_cost_for_two;
    String thumb;               //url for thumbnail
    user_rating userRating;
    location restLocation;

    public restaurantObject(String name,String cuisines,int average_cost_for_two,String thumb,user_rating userRating,location restLocation){

        this.name =  name;
        this.cuisines = cuisines;
        this.average_cost_for_two = average_cost_for_two;
        this.thumb = thumb;
        this.userRating = userRating;
        this.restLocation = restLocation;

    }




}
