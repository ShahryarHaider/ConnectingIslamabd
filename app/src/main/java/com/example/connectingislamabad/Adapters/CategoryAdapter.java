package com.example.connectingislamabad.Adapters;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Domains.CategoryDomain;
import com.example.connectingislamabad.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    //ArrayList Contains Items
    ArrayList<CategoryDomain> items;

    //Constructor
    public CategoryAdapter(ArrayList<CategoryDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View Holder Category Link

        View Inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(Inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());

        @SuppressLint("DiscouragedApi")
        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicPath(),
                "drawable", holder.itemView.getContext().getPackageName());

        if (drawableResourceId != 0) {
            Glide.with(holder.itemView.getContext())
                    .load(drawableResourceId)
                    .into(holder.picImg);
        } else {
            // Log a message or take appropriate action if the resource is not found
            Log.e("CategoryAdapter", "Drawable resource not found for: " + items.get(position).getPicPath());
        }
    }


    //Get Item Count
    @Override
    public int getItemCount() {
        return items.size();
    }

    //ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTxt;
        ImageView picImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            picImg=itemView.findViewById(R.id.catImg);
        }
    }
}
