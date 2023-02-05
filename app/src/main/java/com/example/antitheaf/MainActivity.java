package com.example.antitheaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.antitheaf.databinding.ActivityMainBinding;
import com.example.antitheaf.receiver.BatteryReceiver;
import com.example.antitheaf.service.MyService;
import com.example.antitheaf.service.MyService2;
import com.example.antitheaf.service.MyService3;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;

    boolean selected=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.chargerRemover.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (selected) {

                    binding.chargerRemover.setBackground(getResources().getDrawable(R.drawable.go_bg));
                    stopService(new Intent(MainActivity.this, MyService.class));
                    selected=false;
                }
                else
                {

                    binding.chargerRemover.setBackgroundColor(getColor(R.color.green));
                    startForegroundService(new Intent(MainActivity.this, MyService.class));

                    selected=true;

                }

            }
        });

        binding.motionDetection.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (selected) {

                    binding.motionDetection.setBackground(getResources().getDrawable(R.drawable.go_bg));
                    stopService(new Intent(MainActivity.this, MyService2.class));
                    selected=false;
                }
                else
                {

                    binding.motionDetection.setBackgroundColor(getColor(R.color.green));
                    startForegroundService(new Intent(MainActivity.this, MyService2.class));
                    selected=true;

                }

            }
        });

        binding.pocketRemover.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                if (selected) {


                    binding.pocketRemover.setBackground(getResources().getDrawable(R.drawable.go_bg));
                    stopService(new Intent(MainActivity.this, MyService3.class));
                    selected=false;
                }
                else
                {

                    binding.pocketRemover.setBackgroundColor(getColor(R.color.green));
                    startForegroundService(new Intent(MainActivity.this, MyService3.class));
                    selected=true;

                }

            }
        });

    }



}