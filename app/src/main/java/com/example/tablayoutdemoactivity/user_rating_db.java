package com.example.tablayoutdemoactivity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class user_rating_db {

    @ColumnInfo(name = "AggregateRating")
    private int aggregate_rating;

    @ColumnInfo(name = "Votes")
    private int votes;

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
}
