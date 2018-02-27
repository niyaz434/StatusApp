package com.example.mohamedniyaz.app;

import android.Manifest;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

//    TextView textView;
//    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
   // TextView textView5;
    TextView textView6;
    TextView textView7;
   TextView textView8;
   TextView textView9;
    private MyBroadcastReceiver mybroadcastReceiver;
    SharedPreferences sharedPreferences;
    //private MyBroadcastReceiver mybroadcastReceiver1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // textView = (TextView) findViewById(R.id.servicetext);
        //textView1 = (TextView) findViewById(R.id.servicetext1);
        textView2 = (TextView) findViewById(R.id.servicetext2);
        textView3 = (TextView) findViewById(R.id.servicetext3);
        textView4 = (TextView) findViewById(R.id.servicetext4);
       // textView5 = (TextView) findViewById(R.id.servicetext5);
        textView6 = (TextView) findViewById(R.id.servicetext6);
        textView7 = (TextView) findViewById(R.id.servicetext7);
        textView8 = (TextView) findViewById(R.id.servicetext8);
        textView9= (TextView) findViewById(R.id.servicetext9);

        Toast.makeText(this, "Please turn on the location and network coonection to access location", Toast.LENGTH_LONG).show();

        //Shared Preferences

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        //Editor for shared Preference

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("battery_key",true);
        editor.putBoolean("model_key",true);
        editor.putBoolean("wifi_key",true);
        //editor.putBoolean("mobile_key",true);
        editor.putBoolean("external_key",true);
        editor.putBoolean("internal_key",true);
        editor.putBoolean("location_key",true);
        editor.putBoolean("temp_key",true);
        editor.apply();

        //weather





        //Start intent service
//        Intent intentService = new Intent(this, BackgroundService.class);
//        //intentService.putExtra(BackgroundService.EXTRA_KEY_IN, "Yes Intent Serivce is here");
//        //intentService.putExtra("Value","Battery");
//        intentService.putExtra("API",api);
//        startService(intentService);

//
//        Intent int1 = new Intent(this,BackgroundService.class);
//        int1.putExtra("Value","Battery");
//        startService(int1);

        mybroadcastReceiver = new MyBroadcastReceiver();
        //mybroadcastReceiver1 = new MyBroadcastReceiver();





        //BackGroundTask backGroundTask = new BackGroundTask();
        //backGroundTask.execute("http://api.openweathermap.org/data/2.5/weather?lat=" + String.valueOf(lat)+"&lon=" + String.valueOf(lon) + "&appid=f2941beeefc7cc4e475b1c20ab645d95");
        //Log.d(TAG, "onHandleIntent: "+"http://api.openweathermap.org/data/2.5/weather?lat=" + String.valueOf(lat)+"&lon=" + String.valueOf(lon) + "&appid=f2941beeefc7cc4e475b1c20ab645d95");




        //set the reciver
//        IntentFilter intentFilter = new IntentFilter(BackgroundService.ACTION_MyIntentService);
//        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
//        registerReceiver(mybroadcastReceiver, intentFilter);
//
//        IntentFilter intentFilter1 = new IntentFilter("Battery");
//        intentFilter1.addCategory(Intent.CATEGORY_DEFAULT);
//        registerReceiver(mybroadcastReceiver,intentFilter1);

            IntentFilter intentFilter2 = new IntentFilter("Os");
        intentFilter2.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter2);

            IntentFilter intentFilter3= new IntentFilter("Network");
        intentFilter3.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter3);

        IntentFilter intentFilter4= new IntentFilter("Mobile");
        intentFilter4.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter4);




        IntentFilter intentFilter6 = new IntentFilter("External");
        intentFilter6.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter6);


        IntentFilter intentFilter7 = new IntentFilter("Internal");
        intentFilter7.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter7);


        IntentFilter intentFilter8 = new IntentFilter("Location");
        intentFilter8.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter8);

        IntentFilter intentFilter9 = new IntentFilter("Weather");
        intentFilter9.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter9);


       Context context = getApplicationContext();
        Calendar calendar = Calendar.getInstance();
        AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


        Intent intent = new Intent(context, BackgroundService.class);
        final PendingIntent pendingIntent = PendingIntent.getService(context, 12345, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),100,
                    pendingIntent);
        }


//
//        Intent intent1 = new Intent(getApplicationContext(), MyBroadcastReceiver.class);
//        PendingIntent pendingIntent1= PendingIntent.getBroadcast(this, 1234, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager1=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        alarmManager1.setInexactRepeating(AlarmManager.RTC,System.currentTimeMillis(),000,
//                pendingIntent1);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.select,menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.select){
            Intent intent = new Intent(this,Settings.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mybroadcastReceiver);
        //unregisterReceiver(mybroadcastReceiver1);

    }




    public class MyBroadcastReceiver extends BroadcastReceiver {

        private static final String TAG = "MyBroadcastReciever";

        public MyBroadcastReceiver(){
            super();
        }

        @Override
        public void onReceive(Context context, Intent intent) {


            Toast.makeText(context, "List Updated after one minute", Toast.LENGTH_SHORT).show();


            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);


            Intent batteryStatus = context.registerReceiver(null, ifilter);

            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,1);


//            String result = intent.getStringExtra(BackgroundService.EXTRA_KEY_OUT);
//            //Toast.makeText(MainActivity.this, "this"+result, Toast.LENGTH_SHORT).show();
//            textView.setText(result);
//
//            String result1 = intent.getStringExtra("Value from service");
//            Toast.makeText(MainActivity.this, ""+result1, Toast.LENGTH_SHORT).show();
//            textView1.setText(result1);
//            Log.d("Second Reciever", "onReceive: "+result1);

            //Log.d("Original battery", "onReceive: "+level);
//            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
//            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)//int battPct = level/scale;
           //Log.d("Battery", "onReceive: "+level);
           // String battery1 = String.valueOf(level);
            if(sharedPreferences.getBoolean("battery_key",true)) {

                textView2.setText("Battery level :" + level);
                textView2.setTextSize(18);
            }else {
                textView2.setText("To Track Battery status ,Please change the option and wait for few seconds..");
            }
            String nev = intent.getStringExtra("Os");
            if(sharedPreferences.getBoolean("model_key",true) ){
                textView3.setText(nev);
                textView3.setTextSize(18);
            }else {
                textView3.setText("To Track Model status ,Please change the option and wait for few seconds..");
            }


            String wifi = intent.getStringExtra("WIFI");
            if(sharedPreferences.getBoolean("wifi_key",true)) {
                textView4.setText("Network Mode: "+wifi);
                textView4.setTextSize(18);
            }else {
                textView4.setText("To Track wifi status ,Please change the option and wait for few seconds..");
            }

//            String mob = intent.getStringExtra("MOB");
//            if(sharedPreferences.getBoolean("mobile_key",true)) {
//                textView5.setText(mob);
//                textView5.setTextSize(18);
//            }else {
//                textView5.setText("To Track data status ,Please change the option and wait for few seconds..");
//            }


            long value = Math.round(intent.getLongExtra("EXT",0));
            if(sharedPreferences.getBoolean("external_key",true)) {
                textView6.setText("External Storage: " + value + " MB");
                textView6.setTextSize(18);
            }else {
                textView6.setText("To Track External Storage status ,Please change the option and wait for few seconds..");
            }

            long intvalue = Math.round(intent.getLongExtra("INT",0));
            if(sharedPreferences.getBoolean("internal_key",true)) {
                textView7.setText("Internal Storage :" + intvalue + " MB");
                textView7.setTextSize(18);
            } else {
                textView7.setText("To Track Internal Storage status ,Please change the option and wait for few seconds..");
            }

            String place = intent.getStringExtra("LOC");
            if(sharedPreferences.getBoolean("location_key",true)) {
                Log.d("place", "PLace: " + place);
                textView8.setText("Location  : " + place);
                textView8.setTextSize(18);
            }else {
                textView8.setText("To Track Battery status ,Please change the option and wait for few seconds..");
            }


            String today = intent.getStringExtra("WEA");
            if(sharedPreferences.getBoolean("temp_key",true)) {
                Log.d("weather", "Weather: " + today);
                textView9.setText("Today's Temperature :  " + today + " °C");
                textView9.setTextSize(18);
            }else {
                textView9.setText("To Track Battery status ,Please change the option and wait for few seconds..");
            }



        }
    }
}