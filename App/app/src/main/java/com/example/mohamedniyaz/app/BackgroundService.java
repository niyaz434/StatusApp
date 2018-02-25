package com.example.mohamedniyaz.app;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by mohamedrafiq on 23/02/18.
 */

public class BackgroundService extends IntentService{
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

    public BackgroundService() {
        super("com.example.mohamedniyaz.app.MyIntentService");
    }

    @SuppressLint("NewApi")
    @Override
    protected void onHandleIntent(Intent intent) {
        String wifi = null;
        double availablespace = 0;
        String data = null;
        double internal = 0;
        float internal1 = 0;
        float external = 0 ;


        //get input
        extraIn = intent.getStringExtra(EXTRA_KEY_IN);
        extraOut = "Result from MyIntentService: Hello " + extraIn;

        String battery = intent.getStringExtra("Value");
        String take = "Please i have a battery here"+battery;
        Log.d(TAG, "onHandleIntent: "+battery);


        String brand = Build.BRAND;
        String version = "Model name"+" "+ brand +" "+"Os Version"+ "  "+Build.VERSION.RELEASE;


        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for(NetworkInfo netinfo : networkInfos){
            if(netinfo.getTypeName().equalsIgnoreCase("WIFI"))
            if(netinfo.isConnected()){
                wifi = "Connected to wifi";
            }
            if(netinfo.getTypeName().equalsIgnoreCase("MOBILE"));
            if(netinfo.isConnected()){
                 data = "Connected to data";
            }

        }


        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
             availablespace = statFs.getFreeBlocksLong();
             external = (float) ((availablespace)/1024);




        StatFs statFs1 = new StatFs(Environment.getDataDirectory().getPath());
          internal = statFs1.getFreeBlocksLong();
          internal1 = (float) ((internal)/1024);




//        IntentFilter intentFilter2 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//        intentFilter2.addCategory(Intent.CATEGORY_DEFAULT);
//        registerReceiver(mybroadcastReceiver,intentFilter2);


//        Intent intt = new Intent(Intent.ACTION_BATTERY_CHANGED);
//        intt.setAction("BroadCast");
//        intt.addCategory(Intent.CATEGORY_DEFAULT);
//        sendBroadcast(intt);
        //dummy delay for 5 sec
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } //wait 3 sec

//        Intent intent1 = new Intent();
//        intent1.setAction("Battery");
//        intent1.addCategory(Intent.CATEGORY_DEFAULT);
//        intent1.putExtra("Value from service",take);
//        sendBroadcast(intent1);

        //return result
        Intent intentResponse = new Intent();
        intentResponse.setAction(ACTION_MyIntentService);
        intentResponse.setAction("Battery");
      //  intentResponse.setAction("BroadCast");
        intentResponse.setAction("Os");
        intentResponse.setAction("Network");
        intentResponse.setAction("Mobile");
        intentResponse.setAction("External");
        intentResponse.setAction("Internal");
        intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
        intentResponse.putExtra(EXTRA_KEY_OUT, extraOut);
        intentResponse.putExtra("Value from service",take);
        intentResponse.putExtra("Os",version);
        intentResponse.putExtra("WIFI",wifi);
        intentResponse.putExtra("MOB",data);
        intentResponse.putExtra("EXT",external);
        intentResponse.putExtra("INT",internal1);
        //intentResponse.putExtra(Intent.ACTION_BATTERY_CHANGED,0);
        sendBroadcast(intentResponse);
    }
}
