package com.example.connectingislamabad.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.connectingislamabad.Activities.Main.DetailActivity;
import com.example.connectingislamabad.Activities.Transport.DetailActivityTransport;
import com.example.connectingislamabad.Activities.Transport.TransportActivity;
import com.example.connectingislamabad.Domains.CategoryDomain;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.ViewHolder>{

    //Arraylist TransportDomain
    ArrayList<TransportDomain> items;

    ArrayList<TransportDomain> itemsFull;


    //Constructor
    public TransportAdapter(ArrayList <TransportDomain> items) {

        this.items = items;
        this.itemsFull = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public TransportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_transport,parent,false);
        return new ViewHolder(Inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TransportAdapter.ViewHolder holder, int position) {

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
                Intent intent = new Intent (holder.itemView.getContext(), DetailActivityTransport.class);
                intent.putExtra("object",items.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
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
            for (TransportDomain item : itemsFull) {
                // Split the descriptionTxt into individual routes
                List<String> routesList = Arrays.asList(item.getDescTxt().split(", "));

                // Check if any route in routesList contains the search text
                String finalText = text;
                if(routesList.stream()
                        .anyMatch(route -> route.toLowerCase().contains(finalText))){
                    items.add(item);
                }


            }
        }
        notifyDataSetChanged();
    }



    //ViewHolder for Tranport 1st Category :)
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
