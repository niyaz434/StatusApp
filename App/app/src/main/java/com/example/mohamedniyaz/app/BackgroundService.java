package com.example.mohamedniyaz.app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;



public class BackgroundService extends IntentService  {

    BroadcastReceiver mybroadcastReceiver;

    public static final String ACTION_MyIntentService = "com.example.mohamedniyaz.app.RESPONSE";

    public static final String EXTRA_KEY_IN = "EXTRA_IN";
    public static final String EXTRA_KEY_OUT = "EXTRA_OUT";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    String extraIn;
    String extraOut;
    String placename = null;
    String result = "";
    URL url;
    HttpURLConnection httpURLConnection;
    String placeName ;
    String tem ;


    public BackgroundService() {
        super("com.example.mohamedniyaz.app.MyIntentService");
    }


    @SuppressLint("NewApi")
    @Override
    protected void onHandleIntent(Intent intent) {
        String wifi = null;
        long availablespace = 0;
        String data = null;
        long internal = 0;
        float internal1 = 0;
        float external = 0;
        long megAvailable = 0;
        long megAvailable1 = 0;
        int data1 = 0;



//        //get input
//        extraIn = intent.getStringExtra(EXTRA_KEY_IN);
//        extraOut = "Result from MyIntentService: Hello " + extraIn;
//
//        String battery = intent.getStringExtra("Value");
//        String take = "Please i have a battery here" + battery;
//        Log.d(TAG, "onHandleIntent: " + battery);





        String brand = Build.BRAND;
        String version = "Model name" + " " + brand + " " + "Os Version" + "  " + Build.VERSION.RELEASE;


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo netinfo : networkInfos) {
            if (netinfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netinfo.isConnected()) {
                    wifi = "Connected to wifi";
                } else if (netinfo.getTypeName().equalsIgnoreCase("MOBILE")) ;
                else if (netinfo.isConnected()) {
                    data = "Connected to data";
                }

        }

        File Path = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(Path.getPath());
        availablespace = (statFs.getAvailableBlocksLong()) * (statFs.getBlockSizeLong());
        Log.d(TAG, "getAvailable: " + statFs.getAvailableBlocksLong());
        Log.d(TAG, "getBlock: " + statFs.getBlockSizeLong());
        Log.d(TAG, "onHandleIntent: " + availablespace);
        megAvailable = availablespace / (1024 * 1024);


        StatFs statFs1 = new StatFs(Environment.getRootDirectory().getPath());
        internal = (statFs1.getAvailableBlocksLong()) * (statFs1.getBlockSizeLong());
        Log.d(TAG, "getAvailable: " + statFs1.getAvailableBlocksLong());
        Log.d(TAG, "getBlock: " + statFs1.getBlockSizeLong());
        megAvailable1 = internal / (1048576);


//        IntentFilter intentFilter2 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//        intentFilter2.addCategory(Intent.CATEGORY_DEFAULT);
//        registerReceiver(mybroadcastReceiver,intentFilter2);


//        Intent intt = new Intent(Intent.ACTION_BATTERY_CHANGED);
//        intt.setAction("BroadCast");
//        intt.addCategory(Intent.CATEGORY_DEFAULT);
//        sendBroadcast(intt);
        //dummy delay for 5 sec
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //wait 3 sec

//        Intent intent1 = new Intent();
//        intent1.setAction("Battery");
//        intent1.addCategory(Intent.CATEGORY_DEFAULT);
//        intent1.putExtra("Value from service",take);
//        sendBroadcast(intent1);


        //FOR WEATHER

//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        String provoider = locationManager.getBestProvider(new Criteria(), false);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = locationManager.getLastKnownLocation(provoider);
//
//
//        Double lat = location.getLatitude();
//        Double lon = location.getLongitude();
//
//
//
//
//        BackGroundTask backGroundTask = new BackGroundTask();
//        backGroundTask.execute("http://api.openweathermap.org/data/2.5/weather?lat=" + String.valueOf(lat)+"&lon=" + String.valueOf(lon) + "&appid=f2941beeefc7cc4e475b1c20ab645d95");
//        Log.d(TAG, "onHandleIntent: "+"http://api.openweathermap.org/data/2.5/weather?lat=" + String.valueOf(lat)+"&lon=" + String.valueOf(lon) + "&appid=f2941beeefc7cc4e475b1c20ab645d95");
       // Intent intent1  =new Intent();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provoider = locationManager.getBestProvider(new Criteria(), false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provoider);


        Double lat = location.getLatitude();
        Double lon = location.getLongitude();

        String api = "http://api.openweathermap.org/data/2.5/weather?lat=" + String.valueOf(lat)+"&lon=" + String.valueOf(lon) + "&appid=f2941beeefc7cc4e475b1c20ab645d95";

        // Log.d(TAG, "onHandleIntent: "+api);
        try {
            url = new URL(api);


            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            data1 = inputStreamReader.read();

            while(data1 != -1){

                char current = (char) data1;

                result += current;

                data1 = inputStreamReader.read();

            }


            JSONObject jsonObject = new JSONObject(result);

            JSONObject weatherData = new JSONObject(jsonObject.getString("main"));

            Double temperature = Double.parseDouble(weatherData.getString("temp"));

            int temp = (int) (temperature - 273);

            placeName = jsonObject.getString("name");
            Log.d(TAG, "onPostExecute: "+placeName);

            tem = String.valueOf(temp);
            Log.d(TAG, "Temperature: "+tem);





        } catch (Exception e) {
            e.printStackTrace();
        }












        //return result
        Intent intentResponse = new Intent();
        //intentResponse.setAction(ACTION_MyIntentService);
        //intentResponse.setAction("Battery");
      //  intentResponse.setAction("BroadCast");
        intentResponse.setAction("Os");
        intentResponse.setAction("Network");
        intentResponse.setAction("Mobile");
        intentResponse.setAction("External");
        intentResponse.setAction("Internal");
        intentResponse.setAction("Location");
        intentResponse.setAction("Weather");
        intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
        //intentResponse.putExtra(EXTRA_KEY_OUT, extraOut);
        //intentResponse.putExtra("Value from service",take);
        intentResponse.putExtra("Os",version);
        intentResponse.putExtra("WIFI",wifi);
        intentResponse.putExtra("MOB",data);
        intentResponse.putExtra("EXT",megAvailable);
        intentResponse.putExtra("INT",megAvailable1);
        intentResponse.putExtra("LOC",placeName);
        intentResponse.putExtra("WEA",tem);
        //intentResponse.putExtra(Intent.ACTION_BATTERY_CHANGED,0);
        sendBroadcast(intentResponse);
    }

}
