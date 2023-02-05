package com.example.antitheaf.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.antitheaf.service.MyService;

import java.io.Serializable;

/**
 * Created by Rafaqat Mehmood
 * Whatsapp No:+923101025532
 * 29/06/2022
 */
public class BatteryReceiver extends BroadcastReceiver {

    private MediaPlayer mp;
    private String action;
    Service service;


    @Override
    public void onReceive(Context context, Intent intent) {

        action=intent.getAction();

        if (action.equals(Intent.ACTION_POWER_CONNECTED))
        {
            Toast.makeText(context, "kk", Toast.LENGTH_SHORT).show();
        }
        else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            // Do something when power disconnected
            Toast.makeText(context, "kkkkk", Toast.LENGTH_SHORT).show();
            mp=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
            mp.setLooping(true);
            mp.start();
        }
        else if (action.equals(Intent.ACTION_SCREEN_ON))
        {

        }
        else if (action.equals(Intent.ACTION_SCREEN_OFF))
        {
            Toast.makeText(context, "off", Toast.LENGTH_SHORT).show();
            Log.i("rafaqat", "onReceive off: ");
            mp.stop();


        }

    }
}
