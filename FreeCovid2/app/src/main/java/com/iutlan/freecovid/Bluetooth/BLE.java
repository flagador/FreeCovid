package com.iutlan.freecovid.Bluetooth;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ederdoski.simpleble.models.BluetoothLE;
import com.ederdoski.simpleble.utils.BluetoothLEHelper;
import com.iutlan.freecovid.MainActivity;

import java.util.ArrayList;

public class BLE extends MainActivity {
    BluetoothLEHelper ble;

    protected void onCreate(Bundle savedInstanceState) {
        ble = new BluetoothLEHelper(this);
        if(ble.isReadyForScan()){
            Handler mHandler = new Handler();
            ble.scanLeDevice(true);

            mHandler.postDelayed(() -> {
                setList();
                //--The scan is over, you should recover the found devices.
                for(int i = 0; i < ble.getListDevices().size(); i++){
                    Toast.makeText(getApplicationContext(),ble.getListDevices().get(i).getName(),Toast.LENGTH_LONG).show();
                }
                Log.d("Devices found: ", String.valueOf(ble.getListDevices()));

            }, ble.getScanPeriod());
        }
        super.onCreate(savedInstanceState);
    }
    private void setList(){

        ArrayList<BluetoothLE> aBleAvailable  = new ArrayList<>();

        if(ble.getListDevices().size() > 0){
            for (int i=0; i<ble.getListDevices().size(); i++) {
                aBleAvailable.add(new BluetoothLE(ble.getListDevices().get(i).getName(), ble.getListDevices().get(i).getMacAddress(), ble.getListDevices().get(i).getRssi(), ble.getListDevices().get(i).getDevice()));
            }

        }else{
            Toast.makeText(getApplicationContext(),"Aucun appareil trouvé!",Toast.LENGTH_LONG).show();
        }
    }
}
