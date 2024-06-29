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
import com.example.connectingislamabad.Activities.Category.Detail.DetailFoodCatActivity;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FoodCatAdapter extends RecyclerView.Adapter<FoodCatAdapter.ViewHolder> {

    private ArrayList<FoodCatDomain> items;
    private ArrayList<FoodCatDomain> itemsFull; // Copy of the original list for filtering

    public FoodCatAdapter(ArrayList<FoodCatDomain> items) {
        this.items = items;
        this.itemsFull = new ArrayList<>(items); // Make a copy of the original list
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_subcategory, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitleTxt());

        int drawableResId = holder.itemView.getResources().getIdentifier(items.get(position).getPicImg(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResId)
                .into(holder.picImg);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailFoodCatActivity.class);
            intent.putExtra("object", (Serializable) items.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(String text) {
        items.clear();
        if (text.isEmpty()) {
            items.addAll(itemsFull);
        } else {
            text = text.toLowerCase();
            for (FoodCatDomain item : itemsFull) {
                String finalText = text;
                if (item.getTitleTxt().toLowerCase().contains(text) ||
                        item.getTypeTxt().toLowerCase().contains(text) ||
                        item.getType().stream().anyMatch(type -> type.toLowerCase().contains(finalText))) {
                    items.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView picImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            picImg = itemView.findViewById(R.id.picImg);
        }
    }
}
