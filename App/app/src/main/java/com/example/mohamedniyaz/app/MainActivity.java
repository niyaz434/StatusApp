package com.example.mohamedniyaz.app;

import android.Manifest;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    TextView textView5;
    TextView textView6;
    TextView textView7;
   TextView textView8;
   TextView textView9;
    private MyBroadcastReceiver mybroadcastReceiver;
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
        textView5 = (TextView) findViewById(R.id.servicetext5);
        textView6 = (TextView) findViewById(R.id.servicetext6);
        textView7 = (TextView) findViewById(R.id.servicetext7);
        textView8 = (TextView) findViewById(R.id.servicetext8);
        textView9= (TextView) findViewById(R.id.servicetext9);

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
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),6000,
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
            textView2.setText("Battery level :"+level);
            textView2.setTextSize(18);

            String nev = intent.getStringExtra("Os");
            textView3.setText(nev);
            textView3.setTextSize(18);

            String wifi = intent.getStringExtra("WIFI");
            textView4.setText(wifi);
            textView4.setTextSize(18);

            String mob = intent.getStringExtra("MOB");
            textView5.setText(mob);
            textView5.setTextSize(18);

            long value = Math.round(intent.getLongExtra("EXT",0));
            textView6.setText("External Storage: "+value+" MB");
            textView6.setTextSize(18);

            long intvalue = Math.round(intent.getLongExtra("INT",0));
            textView7.setText("Internal Storage :" + intvalue + " MB");
            textView7.setTextSize(18);

            String place = intent.getStringExtra("LOC");
            Log.d("place", "PLace: "+place);
            textView8.setText("Location  : " +place);
            textView8.setTextSize(18);

            String today = intent.getStringExtra("WEA");
            Log.d("weather", "Weather: "+today);
            textView9.setText("Today's Temperature :  "+ today + " °C");
            textView9.setTextSize(18);


        }
    }
}