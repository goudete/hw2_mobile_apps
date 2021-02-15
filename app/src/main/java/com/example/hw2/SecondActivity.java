package com.example.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

public class SecondActivity extends AppCompatActivity {
    private Button search_button;
    private String json_response;

    private static final String api_url = "https://api.punkapi.com/v2/";
    private static AsyncHttpClient client = new AsyncHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Grab button
        search_button = findViewById(R.id.search_button);

        //add event listener to button, when clicked, call launchNextActivity
        search_button.setOnClickListener(v -> {

            grabUserInput();
            launchNextActivity(v);
        });

    }

    public void grabUserInput() {
        //grab user inputs
        //format into api url

    }

    public void launchNextActivity(View view) {
        //set header
        client.addHeader("Accept", "application/json");
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("api response", new String(responseBody));
                try {
                    JSONObject json = new JSONObject(new String(responseBody));

                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.putExtra("json_response", json.toString());

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String(responseBody));
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("title", "API failed");
            }

        });

    }


}