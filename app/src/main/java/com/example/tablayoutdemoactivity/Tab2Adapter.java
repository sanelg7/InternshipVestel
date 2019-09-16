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

    private final LayoutInflater layoutInflater;

    private List<RestaurantObjectDb> restaurantObjectDbList;

//context cıkar geri alırken
    public Tab2Adapter(Context context/*,List<RestaurantObjectDb> restaurantObjectDbList*/) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
     //   this.restaurantObjectDbList = restaurantObjectDbList;
    }

    @Override
    public int getItemCount() {
        return restaurantObjectDbList.size();
    }

    @Override
    public void onBindViewHolder(FavouritesViewHolder FavouritesViewHolder, int i) {
       /* RestaurantObjectDb favInfo = restaurantObjectDbList.get(i);
        FavouritesViewHolder.vName.setText(favInfo.getName());
        FavouritesViewHolder.vCuisine.setText(favInfo.getCuisines());
       FavouritesViewHolder.vThumb.setImageURI(favInfo.getThumb());*/

       if(restaurantObjectDbList != null){
           RestaurantObjectDb restaurantObjectDb =restaurantObjectDbList.get(i);
           FavouritesViewHolder.setData(restaurantObjectDb.getName(),i);
       }else{
           FavouritesViewHolder.vName.setText("AAAAA");
       }




       // FavouritesViewHolder.vThumb.setImageResource(favInfo.thumb);
    }public void setRest (List<RestaurantObjectDb> restaurantObjectDbs){
        restaurantObjectDbList = restaurantObjectDbs;
        notifyDataSetChanged();
    }

    @Override
    public FavouritesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = layoutInflater
                .inflate(R.layout.card_view, viewGroup, false);
        FavouritesViewHolder viewHolder = new FavouritesViewHolder(itemView);

        return viewHolder;
    }

    public class FavouritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            protected TextView vName;
            protected TextView vCuisine;
            protected ImageView vThumb;
            private int position;

            public FavouritesViewHolder(View v){

                super(v);
                vName = (TextView) v.findViewById(R.id.txtName);
                vCuisine = (TextView) v.findViewById(R.id.txtCuisine);
                vThumb = (ImageView) v.findViewById(R.id.imgThumb);

                v.setOnClickListener(this);

            }public void setData(String fav,int i){
                vName.setText(fav);
                position = i;
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



