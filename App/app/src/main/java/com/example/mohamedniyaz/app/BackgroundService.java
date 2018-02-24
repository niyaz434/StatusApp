package com.example.mohamedniyaz.app;

import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.IBinder;
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

    @Override
    protected void onHandleIntent(Intent intent) {

        //get input
        extraIn = intent.getStringExtra(EXTRA_KEY_IN);
        extraOut = "Result from MyIntentService: Hello " + extraIn;

        String battery = intent.getStringExtra("Value");
        String take = "Please i have a battery here"+battery;
        Log.d(TAG, "onHandleIntent: "+battery);

        IntentFilter intentFilter2 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        intentFilter2.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastReceiver,intentFilter2);
        //dummy delay for 5 sec
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //wait 3 sec

//        Intent intent1 = new Intent();
//        intent1.setAction("Battery");
//        intent1.addCategory(Intent.CATEGORY_DEFAULT);
//        intent1.putExtra("Value from service",take);
//        sendBroadcast(intent1);

        //return result
        Intent intentResponse = new Intent();
        intentResponse.setAction(ACTION_MyIntentService);
        intentResponse.setAction("Battery");
        intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
        intentResponse.putExtra(EXTRA_KEY_OUT, extraOut);
        intentResponse.putExtra("Value from service",take);
        sendBroadcast(intentResponse);
    }
}
