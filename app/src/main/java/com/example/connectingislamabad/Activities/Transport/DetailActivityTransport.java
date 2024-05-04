package com.example.connectingislamabad.Activities.Transport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.connectingislamabad.R;

public class DetailActivityTransport extends AppCompatActivity {

    private TextView titleTxt, locationTxt, bedTxt, guideTxt, wifiTxt, descriptionTxt, ratingTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transport);

        initView();
        setVariable();
    }

    private void setVariable() {
    }

    private void initView() {
    }
}