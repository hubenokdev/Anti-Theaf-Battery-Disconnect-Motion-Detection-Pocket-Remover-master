package com.example.antitheaf.receiver;

import static com.example.antitheaf.service.MyService2.mp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Rafaqat Mehmood
 * Whatsapp No:+923101025532
 * 29/06/2022
 */
public class MotionReceiver extends BroadcastReceiver {

    private String action;



    @Override
    public void onReceive(Context context, Intent intent) {

        action = intent.getAction();

        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            Toast.makeText(context, "off", Toast.LENGTH_SHORT).show();
            Log.i("rafaqat", "onReceive off: ");

            if (mp.isPlaying()) {
                mp.stop();
            }
        }

    }
}
