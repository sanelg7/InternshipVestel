package com.example.tablayoutdemoactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;

import java.util.List;

public class Tab2Adapter extends RecyclerView.Adapter<Tab2Adapter.FavouritesViewHolder> {

    private List<FavouritesInfo> favouritesInfo;

    public Tab2Adapter(List<FavouritesInfo> favouritesInfo) {
        this.favouritesInfo = favouritesInfo;
    }

    @Override
    public int getItemCount() {
        return favouritesInfo.size();
    }

    @Override
    public void onBindViewHolder(FavouritesViewHolder FavouritesViewHolder, int i) {
        FavouritesInfo ci = favouritesInfo.get(i);
        FavouritesViewHolder.vName.setText(ci.name);
        FavouritesViewHolder.vCuisine.setText(ci.cuisine);
       // FavouritesViewHolder.vThumb.setImageResource(ci.thumb);
    }

    @Override
    public FavouritesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view, viewGroup, false);

        return new FavouritesViewHolder(itemView);
    }

    public static class FavouritesViewHolder extends RecyclerView.ViewHolder {

            protected TextView vName;
            protected TextView vCuisine;
            protected ImageView vThumb;

            public FavouritesViewHolder(View v){

                super(v);
                vName = (TextView) v.findViewById(R.id.txtName);
                vCuisine = (TextView) v.findViewById(R.id.txtCuisine);
                vThumb = (ImageView) v.findViewById(R.id.imgThumb);

            }

        }
    }



