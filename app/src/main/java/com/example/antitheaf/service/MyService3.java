package com.example.antitheaf.service;


import static com.example.antitheaf.service.MyService2.mp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.antitheaf.App;
import com.example.antitheaf.MainActivity;
import com.example.antitheaf.R;
import com.example.antitheaf.receiver.MotionReceiver;


public class MyService3 extends Service implements SensorEventListener {

    MotionReceiver motionReceiver;
    SensorManager manager;
    Sensor sensor;

//    public static MediaPlayer mp;

    @Override
    public void onCreate() {
        Toast.makeText(getApplicationContext(), "Service Was Created", Toast.LENGTH_SHORT).show();
        mp= MediaPlayer.create(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (manager != null) {
            sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            if (sensor != null) {
                manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            } else {
                Toast.makeText(this, "Sensor Problem", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "Sensor Not Detected", Toast.LENGTH_SHORT).show();
        }

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         //Yea Method by Default UI thread per run ho raha ha
        //Ager Hum Is Method ko lika ga to simple work kara ga but heavy work kara ga to ANR produce ho jy ga
        // so us cheese ko handle karna ka lea seperate local thread create kara ga r us ko use kara ga

            startServiceForground(intent);


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "Destroy Service", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not Yet Implemented ");
    }


    private void startServiceForground(Intent intent)
    {
        //Get the key
        String input=intent.getStringExtra("key");

        //Create Intent fow which class we are open
        Intent notificationIntent=new Intent(this, MainActivity.class);
        //Crete Pending Intent mean that click notication then jump 2nd app to own app
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 123, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        //Create Notification
        Notification notification=new NotificationCompat.Builder(this, App.CHANNEL_1)
                .setContentTitle("My Service Class Run")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();



        //This line comment then the app service was close r destroy 1 mint
        startForeground(1,notification);


        motionReceiver=new MotionReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(motionReceiver,intentFilter);

        Toast.makeText(getApplicationContext(), "Start Service ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (sensorEvent.values[0]>0)
            {
            }
            else
            {
                mp.setLooping(true);
                mp.start();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
