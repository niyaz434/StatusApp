package com.example.mohamedniyaz.app;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    private MyBroadcastReceiver mybroadcastReceiver;
    //private MyBroadcastReceiver mybroadcastReceiver1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.servicetext);
        textView1 = (TextView) findViewById(R.id.servicetext1);
        textView2 = (TextView) findViewById(R.id.servicetext2);
        textView3 = (TextView) findViewById(R.id.servicetext3);
        textView4 = (TextView) findViewById(R.id.servicetext4);
        textView5 = (TextView) findViewById(R.id.servicetext5);
        textView6 = (TextView) findViewById(R.id.servicetext6);
        textView7 = (TextView) findViewById(R.id.servicetext7);



        //Start intent service
        Intent intentService = new Intent(this, BackgroundService.class);
        intentService.putExtra(BackgroundService.EXTRA_KEY_IN, "Yes Intent Serivce is here");
        intentService.putExtra("Value","Battery");
        //intentService.putExtra("Original",Intent.ACTION_BATTERY_CHANGED);
        startService(intentService);

//
//        Intent int1 = new Intent(this,BackgroundService.class);
//        int1.putExtra("Value","Battery");
//        startService(int1);

        mybroadcastReceiver = new MyBroadcastReceiver();
        //mybroadcastReceiver1 = new MyBroadcastReceiver();




        //set the reciver
        IntentFilter intentFilter = new IntentFilter(BackgroundService.ACTION_MyIntentService);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver, intentFilter);

        IntentFilter intentFilter1 = new IntentFilter("Battery");
        intentFilter1.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter1);

            IntentFilter intentFilter2 = new IntentFilter("Os");
        intentFilter2.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter2);

            IntentFilter intentFilter3= new IntentFilter("Network");
        intentFilter3.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter3);

        IntentFilter intentFilter4= new IntentFilter("Mobile");
        intentFilter4.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter4);

        IntentFilter intentFilter5 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        intentFilter5.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter5);

        IntentFilter intentFilter6 = new IntentFilter("External");
        intentFilter6.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter6);


        IntentFilter intentFilter7 = new IntentFilter("Internal");
        intentFilter7.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter7);




        Intent intent = new Intent(getApplicationContext(), MyBroadcastReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 12345, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager=(AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC,System.currentTimeMillis(),1000,
                pendingIntent);
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

        @Override
        public void onReceive(Context context, Intent intent) {

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,1);

            String result = intent.getStringExtra(BackgroundService.EXTRA_KEY_OUT);
            //Toast.makeText(MainActivity.this, "this"+result, Toast.LENGTH_SHORT).show();
            textView.setText(result);

            String result1 = intent.getStringExtra("Value from service");
            Toast.makeText(MainActivity.this, ""+result1, Toast.LENGTH_SHORT).show();
            textView1.setText(result1);
            Log.d("Second Reciever", "onReceive: "+result1);

            //Log.d("Original battery", "onReceive: "+level);
//            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
//            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)//int battPct = level/scale;
           //Log.d("Battery", "onReceive: "+level);
           // String battery1 = String.valueOf(level);
            textView2.setText("Battery level :"+level);

            String nev = intent.getStringExtra("Os");
            textView3.setText(nev);

            String wifi = intent.getStringExtra("WIFI");
            textView4.setText(wifi);

            String mob = intent.getStringExtra("MOB");
            textView5.setText(mob);

            float value = Math.round(intent.getFloatExtra("EXT",0));
            textView6.setText("External Storage: "+value/100 +" GB");

            float intvalue = Math.round(intent.getFloatExtra("INT",0));
            textView7.setText("Internal Storage :" + intvalue/100 + " GB");


        }
    }
}