package com.example.tablayoutdemoactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Tab2Adapter extends RecyclerView.Adapter<Tab2Adapter.ViewHolder> {


    private Context context;

    private List<RestaurantObjectDb> restaurantObjectDbList;

    public Tab2Adapter(List<RestaurantObjectDb> items) {
        this.restaurantObjectDbList = items;
    }

    @Override
    public Tab2Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        context = view.getContext();
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Tab2Adapter.ViewHolder holder, int position) {

        RestaurantObjectDb item = restaurantObjectDbList.get(position);
        if(item.getThumb()!=null && !item.getThumb().isEmpty()){
            Picasso.get()
                    .load(item.getThumb())
                    .noFade()
                    .into(holder.thumb);
        }else{
            holder.thumb.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.food_thumb));
        }

        RestaurantObjectDb restaurantObjectDb = restaurantObjectDbList.get(position);

        holder.name.setText(restaurantObjectDbList.get(position).getName());
        holder.cuisine.setText(restaurantObjectDbList.get(position).getCuisines());
    }

    @Override
    public int getItemCount() {
        return restaurantObjectDbList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView cuisine;
        public ImageView thumb;


         public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            cuisine = itemView.findViewById(R.id.cuisine);
            thumb = itemView.findViewById(R.id.imgThumb);

        }
    }
}


