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
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.ViewHolder>{

    ArrayList<TransportDomain> items;
    DecimalFormat formatter;

    public TransportAdapter(ArrayList <TransportDomain> items) {
        this.items = items;

        formatter=new DecimalFormat("###,###,###,###");
    }

    @NonNull
    @Override
    public TransportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_transport,parent,false);
        return new ViewHolder(Inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TransportAdapter.ViewHolder holder, int position) {

        holder.titleTxt.setText(items.get(position).getTitleTxt());
        holder.descTxt.setText(items.get(position).getDescTxt());



        int drawableResId = holder.itemView.getResources().getIdentifier(items.get(position).getPicImg(),
                "drawable", holder.itemView.getContext().getPackageName());


        //Glide Holder
        Glide.with(holder.itemView.getContext())
                .load(drawableResId)
                .transform(new CenterCrop(),new GranularRoundedCorners(40,40,40,40))
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
    //ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTxt;
        TextView descTxt;
        ImageView picImg;
        ImageView routeImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            picImg=itemView.findViewById(R.id.picImg);
            routeImg=itemView.findViewById(R.id.routeImg);
            descTxt=itemView.findViewById(R.id.descTxt);
        }
    }
}
