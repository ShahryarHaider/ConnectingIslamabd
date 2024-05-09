package com.example.connectingislamabad.Adapters;

import android.content.Context;
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

    // ArrayList contains items
    @NonNull
    private final ArrayList<CategoryDomain> items;

    // Constructor
    public CategoryAdapter(@NonNull ArrayList<CategoryDomain> items) {
        this.items = items;
    }

    public interface OnCategoryClickListener {
        void onCategoryClick(int position);
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View holder category link
        View categoryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());

        Context context = holder.itemView.getContext();
        String picPath = items.get(position).getPicPath();
        Drawable drawable = ContextCompat.getDrawable(context, context.getResources().getIdentifier(picPath, "drawable", context.getPackageName()));

        if (drawable!= null) {
            Glide.with(context).load(drawable).into(holder.picImg);
        } else {
            Log.e("CategoryAdapter", "Drawable resource not found for item at position " + position + ": " + picPath);
        }

        // Add OnClickListener to category view

    }


    // Get item count
    @Override
    public int getItemCount() {
        return items.size();
    }

    // View holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTxt;
        ImageView picImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            picImg = itemView.findViewById(R.id.catImg);
        }
    }
}