package com.example.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ThirdActivity extends AppCompatActivity {

    private ArrayList<Beer> beers;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Load in data from intent
        // Construct ArrayList of beer objects
        // create an adapter with ArrayList of Villager objects
        Intent intent = getIntent();
        String results = intent.getExtras().getString("json_response");

        //Look up recycler view
        recyclerView = findViewById(R.id.recyclyer_view);
        beers = new ArrayList<>();

        //for each json object, create Beer object and add to list
        try {
            JSONArray beerJsonArr = new JSONArray(results);
            for (int i = 0; i < beerJsonArr.length(); i++) {
                JSONObject beerObject = beerJsonArr.getJSONObject(i);
                Beer beer = new Beer(beerObject.getString("name"),
                        beerObject.getString("tagline"),
                        beerObject.getString("first_brewed"),
                        beerObject.getString("description"),
                        beerObject.getString("image_url"),
                        beerObject.getDouble("abv"),
                        beerObject.getString("food_pairing"),
                        beerObject.getString("brewers_tips"));
                beers.add(beer);
            }

            //Number of results
            String number_of_results = Integer.toString(beers.size());
            String title = getString(R.string.recycler_title, number_of_results);
            TextView results_title = findViewById(R.id.recycler_title);
            results_title.setText(title);

            //create beer adapter to pass in data
            BeerAdapter adapter = new BeerAdapter(this, beers);
            //attach the adapter to recycler view to populate
            recyclerView.setAdapter(adapter);
            //layout manager
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}