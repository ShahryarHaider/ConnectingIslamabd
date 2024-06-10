package com.example.connectingislamabad.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Activities.Category.Detail.DetailPopularCatActivity;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.R;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private List<PopularDomain> popularDomains;
    private Context context;

    public PopularAdapter(List<PopularDomain> popularDomains) {
        this.popularDomains = popularDomains;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularDomain popularDomain = popularDomains.get(position);
        holder.title.setText(popularDomain.getTitle());
        holder.location.setText(popularDomain.getLocation());
        holder.rating.setText(String.valueOf(popularDomain.getRating()));

        int drawableResId = context.getResources().getIdentifier(popularDomain.getPic(), "drawable", context.getPackageName());
        Glide.with(context).load(drawableResId).into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailPopularCatActivity.class);
            intent.putExtra("object", popularDomain);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return popularDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, rating;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            location = itemView.findViewById(R.id.locationTxt);
            rating = itemView.findViewById(R.id.ratingTxt);
            pic = itemView.findViewById(R.id.picImg);
        }
    }
}
