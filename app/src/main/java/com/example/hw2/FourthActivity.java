package com.example.hw2;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {
    String name;
    String description;
    String abv;
    String image;
    String food_pairing;
    String brewsters_tips;
    String first_brewed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("name") &&
                getIntent().hasExtra("description") &&
                getIntent().hasExtra("abv") &&
                getIntent().hasExtra("image") &&
                getIntent().hasExtra("food_pairing") &&
                getIntent().hasExtra("brewsters_tips") &&
                getIntent().hasExtra("first_brewed") ) {

            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            abv = getIntent().getStringExtra("abv");
            image = getIntent().getStringExtra("image");
            food_pairing = getIntent().getStringExtra("food_pairing");
            brewsters_tips = getIntent().getStringExtra("brewsters_tips");
            first_brewed = getIntent().getStringExtra("first_brewed");

            setContent();
        }
    }
    private void setContent() {
//      Get views
        TextView dname = findViewById(R.id.detail_name);
        TextView ddescription = findViewById(R.id.detail_description);
        TextView dabv = findViewById(R.id.detail_abv);
        TextView dfood_pairing = findViewById(R.id.detail_food_pairings);
        TextView dbrewsters_tips = findViewById(R.id.detail_brewster_tips);
        TextView dfirst_brewed = findViewById(R.id.detail_first_brew_date);
        ImageView dimage = findViewById(R.id.detail_image);

//      Set Content
        dname.setText(name);
        ddescription.setText(description);
        dabv.setText(abv);
        dfood_pairing.setText(food_pairing);
        dbrewsters_tips.setText(brewsters_tips);
        dfirst_brewed.setText(first_brewed);

//      Set image
        Picasso.get().load(image).into(dimage);
    }
}


