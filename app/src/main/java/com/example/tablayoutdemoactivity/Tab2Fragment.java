package com.example.tablayoutdemoactivity;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class Tab2Fragment extends Fragment {

  /*  public static RecyclerView.Adapter adapter;
    //RecyclerView recList ;
    private Context context = getContext();
    public static RecyclerView recList;
    List<RestaurantObjectDb> items;
   */
    List<RestaurantObjectDb> rest;
    public static AppDatabase myDatabase;
    Tab2Adapter tab2Adapter = new Tab2Adapter(rest);
    RecyclerView recyclerView;




    public Tab2Fragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_tab2, container, false);



        myDatabase= Room.databaseBuilder(getContext(),AppDatabase.class,"database").allowMainThreadQueries().build();


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rest = myDatabase.restaurantObjectDbDao().getAll();

        Tab2Adapter ca = new Tab2Adapter(rest);
        recyclerView.setAdapter(ca);

        return view;
    }
}



