package com.example.tablayoutdemoactivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;

import java.util.List;

public class Tab2Adapter extends RecyclerView.Adapter<Tab2Adapter.FavouritesViewHolder> {

    private Context context;

    //private List<FavouritesInfo> favouritesInfo;

    private List<RestaurantObjectDb> restaurantObjectDbList;

//context cıkar geri alırken
    public Tab2Adapter(Context context,List<RestaurantObjectDb> restaurantObjectDbList) {
        this.context = context;
        this.restaurantObjectDbList = restaurantObjectDbList;
    }

    @Override
    public int getItemCount() {
        return restaurantObjectDbList.size();
    }

    @Override
    public void onBindViewHolder(FavouritesViewHolder FavouritesViewHolder, int i) {
        RestaurantObjectDb favInfo = restaurantObjectDbList.get(i);
        FavouritesViewHolder.vName.setText(favInfo.getName());
        FavouritesViewHolder.vCuisine.setText(favInfo.getAggregate_rating());




       // FavouritesViewHolder.vThumb.setImageResource(favInfo.thumb);
    }

    @Override
    public FavouritesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view, viewGroup, false);

        return new FavouritesViewHolder(itemView);
    }

    public class FavouritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            protected TextView vName;
            protected TextView vCuisine;
            protected ImageView vThumb;

            public FavouritesViewHolder(View v){

                super(v);
                vName = (TextView) v.findViewById(R.id.txtName);
                vCuisine = (TextView) v.findViewById(R.id.txtCuisine);
                vThumb = (ImageView) v.findViewById(R.id.imgThumb);

                v.setOnClickListener(this);

            }

        @Override
        public void onClick(View view) {
            RestaurantObjectDb restaurantObjectDb = restaurantObjectDbList.get(getAdapterPosition());

            Intent intent = new Intent(context, DetailsActivity.class);
       //     intent.putExtra("restaurantObjectDb",restaurantObjectDb);

           /* Intent intent = new Intent(context,UpdateDb.class);
            intent.putExtra("restaurantObjectDb",restaurantObjectDb);

            context.startActivity(intent);*/
            }
    }
    }



