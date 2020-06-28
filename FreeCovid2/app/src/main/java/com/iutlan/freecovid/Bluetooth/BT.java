package com.iutlan.freecovid.Bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.iutlan.freecovid.MainActivity;

import java.util.ArrayList;
import java.util.List;

import me.aflak.bluetooth.Bluetooth;
import me.aflak.bluetooth.interfaces.BluetoothCallback;
import me.aflak.bluetooth.interfaces.DiscoveryCallback;

public class BT extends MainActivity {
    Bluetooth bluetooth;
    private List<BluetoothDevice> pairedDevices;
    private List<BluetoothDevice> scannedDevices;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // ...
        // Need to ask for bluetooth permissions before calling constructor !
        // Permissions are {BLUETOOTH, BLUETOOTH_ADMIN, ACCESS_COARSE_LOCATION}
        bluetooth = new Bluetooth(this);
        bluetooth.setBluetoothCallback(bluetoothCallback);
        scannedDevices = new ArrayList<>();
        btDiscoveryCallback();
        bluetooth.onStart();
        startScanningInBackground();

        super.onCreate(savedInstanceState);
    }

    public void startScanningInBackground(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        bluetooth.startScanning();
                        sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    public void btDiscoveryCallback(){
        bluetooth.setDiscoveryCallback(new DiscoveryCallback() {
            @Override public void onDiscoveryStarted() {}
            @Override public void onDiscoveryFinished() {}
            @Override public void onDeviceFound(BluetoothDevice device) {
                scannedDevices.add(device);
                if(device.getName() != null){
                    Toast.makeText(getApplicationContext(),"Telephone trouvé : " + device.getName(),Toast.LENGTH_SHORT).show();
                    Log.d("BT", device.getName());
                }
            }
            @Override public void onDevicePaired(BluetoothDevice device) {}
            @Override public void onDeviceUnpaired(BluetoothDevice device) {}

            @Override
            public void onError(int errorCode) {

            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        bluetooth.onStart();
        if(bluetooth.isEnabled()){
            Toast.makeText(getApplicationContext(),"BT déjà Activé !",Toast.LENGTH_LONG).show();
        } else {
            bluetooth.showEnableDialog(BT.this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        bluetooth.onStop();
        Toast.makeText(getApplicationContext(),"BT stoppé !",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bluetooth.onActivityResult(requestCode, resultCode);
    }

    private BluetoothCallback bluetoothCallback = new BluetoothCallback() {
        @Override public void onBluetoothTurningOn() {}
        @Override public void onBluetoothTurningOff() {}
        @Override public void onBluetoothOff() {
            Toast.makeText(getApplicationContext(),"BT Desactivé !",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBluetoothOn() {
            Toast.makeText(getApplicationContext(),"BT Activé !",Toast.LENGTH_LONG).show();
        }



        @Override
        public void onUserDeniedActivation() {
            Toast.makeText(getApplicationContext(),"BT non activé par l'utilisateur !",Toast.LENGTH_LONG).show();
        }
    };
}
