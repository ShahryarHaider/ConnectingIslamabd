package com.example.connectingislamabad.Adapters;

import android.annotation.SuppressLint;
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
import com.example.connectingislamabad.Domains.CategoryDomain;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;

import java.util.ArrayList;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.ViewHolder>{

    ArrayList<TransportDomain> items;

    public TransportAdapter(ArrayList<TransportDomain> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public TransportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_transport,parent,false);
        return new TransportAdapter.ViewHolder(Inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TransportAdapter.ViewHolder holder, int position) {

        holder.titleTxt.setText(items.get(position).getTitleTxt());

        @SuppressLint("DiscouragedApi")
        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicImg(), "drawable", holder.itemView.getContext().getPackageName());

        if (drawableResourceId != 0) {
            Glide.with(holder.itemView.getContext())
                    .load(drawableResourceId)
                    .into(holder.picImg);
        } else {
            // Log a message or take appropriate action if the resource is not found
            Log.e("TransportAdapter", "Drawable resource not found for: " + items.get(position).getPicImg());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    //ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTxt;
        ImageView picImg;
        ImageView picBg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            picImg=itemView.findViewById(R.id.picImg);
            picBg=itemView.findViewById(R.id.picBg);
        }
    }
}
