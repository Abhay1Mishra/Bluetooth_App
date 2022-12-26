package com.example.bluetooth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity2 extends AppCompatActivity {
    Button bton ,btoff;
    Image image;
    
    BluetoothAdapter bluetoothAdapter;
    private static final int enable =0;
    private static final int discover =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bton = findViewById(R.id.bton);
        btoff = findViewById(R.id.btoff);



        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        bton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.BLUETOOTH_CONNECT)== PackageManager.PERMISSION_DENIED){
                    if(Build.VERSION.SDK_INT>=31){
                        ActivityCompat.requestPermissions(MainActivity2.this,new String[]{Manifest.permission.BLUETOOTH_CONNECT},100);
                        return;
                    }
                    BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                    if(Build.VERSION.SDK_INT>=31){
                        bluetoothAdapter = bluetoothManager.getAdapter();
                    }
                    else{
                        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    }

                }
                if(bluetoothAdapter.disable()){
                    bluetoothAdapter.enable();
                    Toast.makeText(MainActivity2.this, "Turn on...", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame,new Fragment1()).commit();
                }


            }
        });



        btoff.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isEnabled()){
                    bluetoothAdapter.disable();
                    Toast.makeText(MainActivity2.this, "Turning off...", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity2.this, "Already off...", Toast.LENGTH_SHORT).show();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,new Fragment2()).commit();
            }
        });

    }
}