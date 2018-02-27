package com.example.mohamedniyaz.app;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;


public class BackGroundTask extends AsyncTask<String,Void,String>  {
    //public AsyncResponse delegate = null;

    String placeName = "";
    String tem = "";
    String result = "";
    URL url;
    HttpURLConnection httpURLConnection;


    @Override
    protected String doInBackground(String... strings) {

        try {
            url = new URL(strings[0]);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();


            while(data != -1){

                char current = (char) data;

                result += current;

                data = inputStreamReader.read();

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

            JSONObject weatherData = new JSONObject(jsonObject.getString("main"));

            Double temperature = Double.parseDouble(weatherData.getString("temp"));

            int temp = (int) (temperature - 273);

           placeName = jsonObject.getString("name");
            //Log.d(TAG, "onPostExecute: "+placeName);

           tem = String.valueOf(temp);

            //delegate.processFinish(placeName);




        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}

