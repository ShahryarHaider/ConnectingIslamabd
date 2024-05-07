package com.example.connectingislamabad.Activities.Transport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;

public class DetailActivityTransport extends AppCompatActivity {

    //TextView
    private TextView titleTxt, descTxt;

    //Calling Transport Domain
    private TransportDomain item;

    //ImageView

    private ImageView picImg, backBtn, routeImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transport);


        initView(); // Initialize views first
        setVariable(); // Set text and other variables afterwards
    }

    private void initView() {
        titleTxt = findViewById(R.id.titleTxt);
        descTxt = findViewById(R.id.descTxt);
        backBtn = findViewById(R.id.backBtn);
        picImg = findViewById(R.id.picImg);
        routeImg = findViewById(R.id.routeImg);
        //Contains Only Text Data
    }

    private void setVariable() {
        item = (TransportDomain) getIntent().getSerializableExtra("object");
        titleTxt.setText(item.getTitleTxt());
        descTxt.setText(item.getDescTxt());


        int drawableResId = getResources().getIdentifier(item.getPicImg(), "drawable", getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(picImg);

        //Backbutton Click
        backBtn.setOnClickListener(v -> finish());

        //Finding View By Id Items
    }
}