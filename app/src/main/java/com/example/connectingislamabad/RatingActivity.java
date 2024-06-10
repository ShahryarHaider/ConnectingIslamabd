package com.example.connectingislamabad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RatingActivity extends AppCompatActivity {

    TextView rating_value, showRating;
    EditText review_text;

    float rateValue;
    String temp;
    RatingBar ratingBar;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        rating_value = findViewById(R.id.rating_value);
        ratingBar = findViewById(R.id.rating_bar);
        review_text = findViewById(R.id.review_text);
        submit = findViewById(R.id.submit_btn);
        showRating = findViewById(R.id.showRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();

                if (rateValue<=1 && +rateValue>=0)
                    rating_value.setText("Bad" +rateValue+ " /5");
                else if (rateValue<=2 && +rateValue>=1)
                    rating_value.setText("Average" +rateValue+ " /5");
                else if (rateValue<=3 && +rateValue>=2)
                    rating_value.setText("Good" +rateValue+ " /5");
                else if (rateValue<=4 && +rateValue>=3)
                    rating_value.setText("Very Good" +rateValue+ " /5");
                else if (rateValue<=5 && +rateValue>=4)
                    rating_value.setText("Best" +rateValue+ " /5");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = rating_value.getText().toString();
                showRating.setText(" Your Rating : \n" +temp +"\n" + review_text.getText());
                review_text.setText("");
                ratingBar.setRating(0);
                rating_value.setText("");
            }
        });

    }
}