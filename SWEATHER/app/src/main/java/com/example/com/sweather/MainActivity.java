package com.example.com.sweather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Button check;
    TextView weather;
    EditText city;
    ProgressBar progressBar;

    public class DownloadTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url ;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data!=-1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                String weatherInfo = jsonObject.getString("current");
                Log.i("main",weatherInfo);

                JSONObject jsonInnerObject = new JSONObject(weatherInfo);
                String wea = "Temperature : "+jsonInnerObject.getString("temp_c")+"`C\n";
                weatherInfo = jsonInnerObject.getString("condition");
                JSONObject moreInnerObject = new JSONObject(weatherInfo);
                wea += "Weather : ";
                wea += moreInnerObject.getString("text");
                progressBar.setVisibility(View.INVISIBLE);
                weather.setText(wea);

            } catch (Exception e) {
                e.printStackTrace();
                weather.setText("No Detailes Found");
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        check = findViewById(R.id.button);
        weather = findViewById(R.id.weather);
        city = findViewById(R.id.editText);
        progressBar.setVisibility(View.INVISIBLE);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(city.getWindowToken(),0);
                progressBar.setVisibility(View.VISIBLE);
                DownloadTask task = new DownloadTask();
                String location = null;
                try {
                    location = URLEncoder.encode(city.getText().toString(),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String weatherr = "http://api.apixu.com/v1/current.json?key=d4ff95b74b9b4caab47102257180601&q="+location;
                task.execute(weatherr);
            }
        });
//        weather.setText(weatherr);
    }
}
