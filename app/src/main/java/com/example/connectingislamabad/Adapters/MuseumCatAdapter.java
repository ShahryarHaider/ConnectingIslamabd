package com.example.connectingislamabad.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Activities.Category.Detail.DetailFoodCatActivity;
import com.example.connectingislamabad.Activities.Category.Detail.DetailMuseumCatActivity;
import com.example.connectingislamabad.Domains.MuseumCatDomain;
import com.example.connectingislamabad.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;

public class MuseumCatAdapter extends RecyclerView.Adapter<MuseumCatAdapter.ViewHolder> {

    ArrayList<MuseumCatDomain> items;
    private GoogleMap mMap;

    public MuseumCatAdapter(ArrayList<MuseumCatDomain> items, GoogleMap mMap) {
        this.items = items;
        this.mMap = mMap;
    }

    @NonNull
    @Override
    public MuseumCatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single item in the RecyclerView
        View Inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_subcategory, parent, false);

        // Create a new ViewHolder object using the inflated view
        return new MuseumCatAdapter.ViewHolder(Inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MuseumCatAdapter.ViewHolder holder, int position) {
        //Holder of text data only
        holder.titleTxt.setText(items.get(position).getTitleTxt());

        int drawableResId = holder.itemView.getResources().getIdentifier(items.get(position).getPicImg(),
                "drawable", holder.itemView.getContext().getPackageName());

        //Glide Holder
        Glide.with(holder.itemView.getContext())
                .load(drawableResId)
                .into(holder.picImg);

        //Fetch Info For DetailFoodActivity Class
        holder.itemView.setOnClickListener(v ->{
            {
                Intent intent = new Intent (holder.itemView.getContext(), DetailMuseumCatActivity.class);
                intent.putExtra("object", (Serializable) items.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

//        MuseumCatDomain item = items.get(position);
//
//        LatLng location = new LatLng(item.getLatitude(), item.getLongitude());
//        mMap.addMarker(new MarkerOptions().position(location).title(item.getTitleTxt()));

//        holder.itemView.setOnClickListener(v -> {
//            // Update camera position when item is clicked
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
//        });
    }

    // COUNT THE SIZE OF DATA IN RECYCLER VIEW
    @Override
    public int getItemCount() {
        return items.size();
    }

    // THUMBNIL OF RECYCLER VIEW
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView picImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            picImg=itemView.findViewById(R.id.picImg);
        }
    }
}

