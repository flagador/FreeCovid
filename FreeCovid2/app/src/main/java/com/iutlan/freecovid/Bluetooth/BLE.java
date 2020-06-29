package com.iutlan.freecovid.Bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothProfile;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ederdoski.simpleble.interfaces.BleCallback;
import com.ederdoski.simpleble.models.BluetoothLE;
import com.ederdoski.simpleble.utils.BluetoothLEHelper;
import com.ederdoski.simpleble.utils.Constants;
import com.iutlan.freecovid.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class BLE extends MainActivity {
    BluetoothLEHelper ble;

    private BleCallback bleCallbacks(){
        return new BleCallback(){

            @Override
            public void onBleConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                super.onBleConnectionStateChange(gatt, status, newState);

                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Connected to GATT server.", Toast.LENGTH_SHORT).show());
                }

                if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Disconnected from GATT server.", Toast.LENGTH_SHORT).show());
                }
            }

            @Override
            public void onBleServiceDiscovered(BluetoothGatt gatt, int status) {
                super.onBleServiceDiscovered(gatt, status);
                if (status != BluetoothGatt.GATT_SUCCESS) {
                    Log.e("Ble ServiceDiscovered","onServicesDiscovered received: " + status);
                }
            }

            @Override
            public void onBleCharacteristicChange(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                super.onBleCharacteristicChange(gatt, characteristic);
                Log.i("BluetoothLEHelper","onCharacteristicChanged Value: " + Arrays.toString(characteristic.getValue()));
            }

            @Override
            public void onBleRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onBleRead(gatt, characteristic, status);

                if (status == BluetoothGatt.GATT_SUCCESS) {
                    Log.i("TAG", Arrays.toString(characteristic.getValue()));
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "onCharacteristicRead : "+Arrays.toString(characteristic.getValue()),             Toast.LENGTH_SHORT).show());
                }
            }

            @Override
            public void onBleWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onBleWrite(gatt, characteristic, status);
                runOnUiThread(() -> Toast.makeText(getApplicationContext(), "onCharacteristicWrite Status : " + status, Toast.LENGTH_SHORT).show());
            }
        };
    }

    protected void onCreate(Bundle savedInstanceState) {

        ble = new BluetoothLEHelper(this);
        scanBLE();
        tenterConnexion();

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

    private void scanBLE(){
        if(ble.isReadyForScan()){
            Handler mHandler = new Handler();
            ble.scanLeDevice(true);

            mHandler.postDelayed(() -> {
                setList();
                //--The scan is over, you should recover the found devices.
                for(int i = 0; i < ble.getListDevices().size(); i++){
                    Toast.makeText(getApplicationContext(),String.valueOf(ble.getListDevices().get(i)),Toast.LENGTH_LONG).show();
                    Log.d("Devices found: ", String.valueOf(ble.getListDevices()));
                }


            }, ble.getScanPeriod());
        }
    }

    private void tenterConnexion(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        if(ble.getListDevices().size()>0){
                            for(int i = 0; i <ble.getListDevices().size(); i++){
                                BluetoothLE  itemValue = (BluetoothLE) ble.getListDevices().get(i);
                                ble.connect(itemValue.getDevice(), bleCallbacks());
                                Log.d("CONNEXION BLE","est connecte =" +ble.isConnected());
                                if (ble.isConnected()){
                                    String msg = "message bluetooth";
                                    byte[] aBytes = msg.getBytes();
                                    //ble.write(Constants.SERVICE_COLLAR_INFO,Constants.CHARACTERISTIC_GEOFENCE, aBytes);
                                }
                            }
                        }
                        sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

}
