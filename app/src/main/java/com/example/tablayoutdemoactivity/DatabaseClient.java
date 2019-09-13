package com.example.tablayoutdemoactivity;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient instance;

    //db obj
    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {

        this.context = context;

        //creating the app database with Room database builder
        //db is the name of the database

        appDatabase = Room.databaseBuilder
                (context, AppDatabase.class, "db")
                .build();

    }

        public static synchronized DatabaseClient getInstance (Context context){
            if (instance == null) {
                instance = new DatabaseClient(context);
            }
            return instance;
        }

        public AppDatabase getAppDatabase(){
            return appDatabase;
        }
    }
