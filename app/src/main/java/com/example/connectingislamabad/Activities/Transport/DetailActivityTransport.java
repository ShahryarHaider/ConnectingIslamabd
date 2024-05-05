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

        initView();
        setVariable();
    }

    private void setVariable() {
        titleTxt = findViewById(R.id.titleTxt);
        descTxt = findViewById(R.id.descTxt);
        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backBtn);
        routeImg = findViewById(R.id.routeImg);
    }

    private void initView() {
        item = (TransportDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitleTxt());



        int drawableResId = getResources().getIdentifier(item.getPicImg(), "drawable", getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(picImg);

        //Backbutton Click
        backBtn.setOnClickListener(v -> finish());

        //Finding View By Id Items
    }
}