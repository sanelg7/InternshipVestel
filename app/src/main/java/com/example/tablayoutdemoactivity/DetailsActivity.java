package com.example.tablayoutdemoactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    public static AppDatabase myDatabase;
    private final int REQUEST_CODE = 2;
    RatingBar ratingBar;
    Button rateButton;
    List<RestaurantObjectDb> rest;
    private TextView restName;
    private TextView restVote;
    private TextView cuisineInfo;
    private TextView costForTwo;
    private TextView cityName;
    private Button addFav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Intent intent = getIntent();

        String title = intent.getStringExtra("Title");
        String cuisine = intent.getStringExtra("Cuisine");
        int vote = intent.getIntExtra("Votes", 0);
        int cost = intent.getIntExtra("Cost", 0);
        String thumb = intent.getStringExtra("Thumb");
        String city = intent.getStringExtra("City");


        // rest.delete(restaurantObjectDb);


        rateButton = (Button) findViewById(R.id.rateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rating = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
            }
        });


        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        restName = (TextView) findViewById(R.id.rest_name);
        if (title != null) {
            restName.setText(title);
        } else {
            restName.setText("No information available...");
        }
        cuisineInfo = (TextView) findViewById(R.id.rest_info);
        if (cuisine != null) {
            cuisineInfo.setText(cuisine);

        } else {
            cuisineInfo.setText("No information available...");
        }


        restVote = (TextView) findViewById(R.id.rest_rating);
        if (String.valueOf(vote) != null) {
            restVote.setText(String.valueOf(vote));

        } else {
            restVote.setText("No information available...");
        }

        costForTwo = (TextView) findViewById(R.id.cost_for_two);
        if (String.valueOf(cost) != null) {
            costForTwo.setText(String.valueOf(cost));
        } else {
            costForTwo.setText("No information available...");
        }
        cityName = (TextView) findViewById(R.id.city_name);
        if (city != null) {
            cityName.setText(city);
        } else {
            cityName.setText("No information available...");
        }


        addFav = (Button) findViewById(R.id.addFav);
        addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DetailsActivity.this, Tab2Fragment.class);
                startActivity(i);
            }
        });
        addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent returnIntent = getIntent();
                returnIntent.putExtra("returnData", "From SecondAct");

                setResult(RESULT_OK, returnIntent);
                finish();
            }


        });
    }


}
