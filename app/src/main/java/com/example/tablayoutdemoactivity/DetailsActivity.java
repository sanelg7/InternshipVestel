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

public class DetailsActivity extends AppCompatActivity {
    private  final int REQUEST_CODE = 2;

    private TextView restName;
    private TextView restRating;
    private TextView restInfo;
    RatingBar ratingBar;
    Button rateButton;

    private Button addFav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        rateButton = (Button) findViewById(R.id.rateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rating = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(),rating,Toast.LENGTH_LONG).show();
            }
        });


        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        restName = (TextView) findViewById(R.id.rest_name);



        restInfo = (TextView) findViewById(R.id.rest_info);
        restRating = (TextView) findViewById(R.id.rest_rating);

        addFav = (Button) findViewById(R.id.addFav);
        addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // restaurantObject rest = new restaurantObject(restName.toString());


                Intent i = new Intent(DetailsActivity.this,Tab2Fragment.class);
                startActivity(i);
            }
        });

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            String rating = extras.getString("rating new page");
            restRating.setText(rating);
            String name = extras.getString("name new page");
            restName.setText(name);
            String info = extras.getString("info new page");
            restInfo.setText(info);

        }
        addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent returnIntent = getIntent();
                returnIntent.putExtra("returnData","From SecondAct");

                setResult(RESULT_OK,returnIntent);
                finish();
            }

        });
    }
}
