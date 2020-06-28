package com.iutlan.freecovid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import me.aflak.bluetooth.Bluetooth;
import me.aflak.bluetooth.interfaces.BluetoothCallback;

public class BT extends MainActivity {
    Bluetooth bluetooth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // ...
        // Need to ask for bluetooth permissions before calling constructor !
        // Permissions are {BLUETOOTH, BLUETOOTH_ADMIN, ACCESS_COARSE_LOCATION}
        bluetooth = new Bluetooth(this);
        bluetooth.setBluetoothCallback(bluetoothCallback);
        super.onCreate(savedInstanceState);
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
