package com.example.tablayoutdemoactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

public class Tab2Adapter extends RecyclerView.Adapter<Tab2Adapter.MyViewHolder> {

    private Context mContext;
    private String[] mTitle;





    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }
    public Tab2Adapter(Context mContext,String[] title) {
        this.mContext = mContext;
        this.mTitle = title;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {

        holder.title.setText(mTitle[i]);

    }

    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}
