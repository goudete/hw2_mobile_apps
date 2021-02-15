package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.entity.mime.Header;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button main_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Grab button
        main_button = findViewById(R.id.home_button);

        //add event listener to button, when clicked, call launchNextActivity
        main_button.setOnClickListener(v -> {
            launchNextActivity(v);
        });

    }

    public void launchNextActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);

    }


}