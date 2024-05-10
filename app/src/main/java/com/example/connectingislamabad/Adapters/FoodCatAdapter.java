package com.example.connectingislamabad.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Activities.Category.DetailFoodCatActivity;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.R;


import java.io.Serializable;
import java.util.ArrayList;

public class FoodCatAdapter extends RecyclerView.Adapter<FoodCatAdapter.ViewHolder> {

    ArrayList<FoodCatDomain> items;

    public FoodCatAdapter(ArrayList<FoodCatDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single item in the RecyclerView
        View Inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_subcategory, parent, false);

        // Create a new ViewHolder object using the inflated view
        return new ViewHolder(Inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Holder of text data only
        holder.titleTxt.setText(items.get(position).getTitleTxt());

        int drawableResId = holder.itemView.getResources().getIdentifier(items.get(position).getPicImg(),
                "drawable", holder.itemView.getContext().getPackageName());

        //Glide Holder
        Glide.with(holder.itemView.getContext())
                .load(drawableResId)
                .into(holder.picImg);

        //Fetch Info For TransportActivity Class
        holder.itemView.setOnClickListener(v ->{
            {
                Intent intent = new Intent (holder.itemView.getContext(), DetailFoodCatActivity.class);
                intent.putExtra("object", (Serializable) items.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //contains Data that is visible on the Tranport 1st Display
        TextView titleTxt;
        ImageView picImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt=itemView.findViewById(R.id.titleTxt);
            picImg=itemView.findViewById(R.id.picImg);
        }
    }
}


