package com.example.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.entity.mime.Header;

public class SecondActivity extends AppCompatActivity {
    private Button searchButton;
    private EditText brewName;
    private EditText startDate;
    private EditText endDate;
    private Switch highPoint;
    private String json_response;

    private String api_url;
    private static AsyncHttpClient client = new AsyncHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        //Grab ui elements
        searchButton = findViewById(R.id.search_button);
        brewName = findViewById(R.id.brew_name);
        startDate = findViewById(R.id.start_date);
        endDate = findViewById(R.id.end_date);
        highPoint = findViewById(R.id.high_point);



        //add event listener to button, when clicked, call launchNextActivity
        searchButton.setOnClickListener(v -> {

            grabUserInput(brewName, startDate, endDate, highPoint);
            launchNextActivity(v);
        });

    }

    public void grabUserInput(EditText brewName, EditText startDate, EditText endDate, Switch highPoint) {
        // grab user inputs
        String brew = brewName.getText().toString();
        String start = startDate.getText().toString();
        String end = endDate.getText().toString();
        Boolean highP = highPoint.isChecked();
        api_url = "https://api.punkapi.com/v2/beers?";

        // Validate inputs
        Boolean validBrew = validateBrew(brew);
        Boolean validDate = validateDate(start, end);

        if (validBrew) {
            brew.replaceAll(" ", "_");
            //check if first param
            if (api_url.substring(api_url.length()-1) == "?") {
                String brew_param = "beer_name="+brew;
                api_url += brew_param;
            } else {
                String brew_param = "&beer_name="+brew;
                api_url += brew_param;
            }

        }
        if (validDate) {
            if (!start.isEmpty()) {
                if (api_url.substring(api_url.length()-1) == "?") {
                    String start_param = "brewed_after="+start;
                    api_url += start_param;
                } else {
                    String start_param = "&brewed_after="+start;
                    api_url += start_param;
                }
            }
            if (!end.isEmpty()) {
                if (api_url.substring(api_url.length()-1) == "?") {
                    String end_param = "brewed_before="+end;
                    api_url += end_param;
                } else {
                    String end_param = "&brewed_before="+end;
                    api_url += end_param;
                }

            }
        }
        if (highP) {
            if (api_url.substring(api_url.length()-1) == "?") {
                String point_param = "abv_gt=4";
                api_url += point_param;
            } else {
                String point_param = "&abv_gt=4";
                api_url += point_param;
            }

        }
        Log.d("API URL:", api_url);
    }

    public Boolean validateBrew(String brew) {
        if (brew.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean validateDate(String startDate, String endDate) {
        if (startDate.isEmpty() && endDate.isEmpty()) {
            return false;
        }
        else if (!startDate.isEmpty() && !startDate.matches("^\\d{2}-\\d{4}$")) {
            //display toast
            CharSequence text = "Invalid date, expecting mm-yyyy";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            return false;
        }
        else if (!endDate.isEmpty() && !endDate.matches("^\\d{2}-\\d{4}$")) {
            //display toast
            CharSequence text = "Invalid date, expecting mm-yyyy";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            return false;
        }
        else {
            return true;
        }

    }

    public void launchNextActivity(View view) {
        //set header
        client.addHeader("Accept", "application/json");
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                Log.d("api response", new String(responseBody));
                try {
                    JSONArray json = new JSONArray(new String(responseBody));

                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.putExtra("json_response", json.toString());
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String(responseBody));
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("title", "API failed");
            }
        });

    }


}