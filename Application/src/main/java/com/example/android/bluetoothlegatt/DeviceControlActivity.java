/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * For a given BLE device, this Activity provides the user interface to connect, display data,
 * and display GATT services and characteristics supported by the device.  The Activity
 * communicates with {@code BluetoothLeService}, which in turn interacts with the
 * Bluetooth LE API.
 */
public class DeviceControlActivity extends Activity {
    private final static String TAG = DeviceControlActivity.class.getSimpleName();

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    private TextView mConnectionState;
    private TextView mDataFieldACTION_DATA_AVAILABLE;
    private TextView mDataFieldEXTRA_DATA_BATTERY_STATUS;
    private TextView mDataFieldEXTRA_DATA_SYSTOLIC_PROGRESS;
    private TextView mDataFieldEXTRA_DATA_BP_SYSTOLIC;
    private TextView mDataFieldEXTRA_DATA_BP_DIASTOLIC;
    private TextView mDataFieldEXTRA_DATA_BP_HEART_RATE;
    private TextView mDataFieldEXTRA_DATA_ERROR;
    /*
                EXTRA_DATA_BATTERY_STATUS,
                EXTRA_DATA_SYSTOLIC_PROGRESS,
                EXTRA_DATA_BP_SYSTOLIC,
                EXTRA_DATA_BP_DIASTOLIC,
                EXTRA_DATA_BP_HEART_RATE,
                EXTRA_DATA_ERROR
                */
    private String mDeviceName;
    private String mDeviceAddress;
    private ExpandableListView mGattServicesList;
    private BluetoothLeService mBluetoothLeService;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics =
            new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
    private boolean mConnected = false;
    private BluetoothGattCharacteristic mNotifyCharacteristic;

    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";

    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    // Handles various events fired by the Service.
    // ACTION_GATT_CONNECTED: connected to a GATT server.
    // ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
    // ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
    // ACTION_DATA_AVAILABLE: received data from the device.  This can be a result of read
    //                        or notification operations.
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                updateConnectionState(R.string.connected);
                invalidateOptionsMenu();
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                updateConnectionState(R.string.disconnected);
                invalidateOptionsMenu();
                clearUI();

            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the user interface.
                displayGattServices(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                //displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA), "ACTION_DATA_AVAILABLE");
//                Log.d("Data Tag", intent.getStringExtra(BluetoothLeService.EXTRA_DATA_BATTERY_STATUS));

                if (intent.getStringExtra(BluetoothLeService.EXTRA_DATA_BATTERY_STATUS) != null) {
                    displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA_BATTERY_STATUS), "EXTRA_DATA_BATTERY_STATUS");
                    Log.d("Data Tag", intent.getStringExtra(BluetoothLeService.EXTRA_DATA_BATTERY_STATUS));
                }
                if (intent.getStringExtra(BluetoothLeService.EXTRA_DATA_SYSTOLIC_PROGRESS) != null) {
                    displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA_SYSTOLIC_PROGRESS), "EXTRA_DATA_SYSTOLIC_PROGRESS");
                    Log.d("Data Tag", intent.getStringExtra(BluetoothLeService.EXTRA_DATA_SYSTOLIC_PROGRESS));
                }
                if (intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_SYSTOLIC, 0) != 0 && intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_DIASTOLIC, 0) != 0 && intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_HEART_RATE, 0) != 0) {
                    Log.d("Debugger Tag", "Checking EXTRA_DATA_BP_SYSTOLIC Value 1");
                    displayData(String.valueOf(intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_SYSTOLIC, 0)), "EXTRA_DATA_BP_SYSTOLIC");
                    Log.d("Data Tag", String.valueOf(intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_SYSTOLIC, 0)));
                    Log.d("Debugger Tag", "Checking EXTRA_DATA_BP_HEART_RATE Value 2");
                    displayData(String.valueOf(intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_DIASTOLIC, 0)), "EXTRA_DATA_BP_DIASTOLIC");
                    Log.d("Data Tag", String.valueOf(intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_DIASTOLIC, 0)));
                    Log.d("Debugger Tag", "Checking EXTRA_DATA_BP_HEART_RATE Value 3");
                    displayData(String.valueOf(intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_HEART_RATE, 0)), "EXTRA_DATA_BP_HEART_RATE");
                    Log.d("Data Tag", String.valueOf(intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_HEART_RATE, 0)));
                }
                if (intent.getStringExtra(BluetoothLeService.EXTRA_DATA_ERROR) != null) {
                    displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA_ERROR), "EXTRA_DATA_ERROR");
                    Log.d("Data Tag", intent.getStringExtra(BluetoothLeService.EXTRA_DATA_ERROR));
                }
            }
        }
    };

    // If a given GATT characteristic is selected, check for supported features.  This sample
    // demonstrates 'Read' and 'Notify' features.  See
    // http://d.android.com/reference/android/bluetooth/BluetoothGatt.html for the complete
    // list of supported characteristic features.
    private final ExpandableListView.OnChildClickListener servicesListClickListner =
            new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                            int childPosition, long id) {
                    if (mGattCharacteristics != null) {
                        final BluetoothGattCharacteristic characteristic =
                                mGattCharacteristics.get(groupPosition).get(childPosition);
                        final int charaProp = characteristic.getProperties();
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                            // If there is an active notification on a characteristic, clear
                            // it first so it doesn't update the data field on the user interface.
                            if (mNotifyCharacteristic != null) {
                                mBluetoothLeService.setCharacteristicNotification(
                                        mNotifyCharacteristic, false);
                                mNotifyCharacteristic = null;
                            }
                            mBluetoothLeService.readCharacteristic(characteristic);
                        }
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                            mNotifyCharacteristic = characteristic;
                            mBluetoothLeService.setCharacteristicNotification(
                                    characteristic, true);
                        }
                        return true;
                    }
                    return true;
                }
    };

    private void clearUI() {
        mGattServicesList.setAdapter((SimpleExpandableListAdapter) null);
        mDataFieldACTION_DATA_AVAILABLE.setText(R.string.no_data);
        mDataFieldEXTRA_DATA_BATTERY_STATUS.setText(R.string.no_data);
        mDataFieldEXTRA_DATA_SYSTOLIC_PROGRESS.setText(R.string.no_data);
        mDataFieldEXTRA_DATA_BP_SYSTOLIC.setText(R.string.no_data);
        mDataFieldEXTRA_DATA_BP_DIASTOLIC.setText(R.string.no_data);
        mDataFieldEXTRA_DATA_BP_HEART_RATE.setText(R.string.no_data);
        mDataFieldEXTRA_DATA_ERROR.setText(R.string.no_data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gatt_services_characteristics);

        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        // Sets up UI references.
        ((TextView) findViewById(R.id.device_address)).setText(mDeviceAddress);
        mGattServicesList = (ExpandableListView) findViewById(R.id.gatt_services_list);
        mGattServicesList.setOnChildClickListener(servicesListClickListner);
        mConnectionState = (TextView) findViewById(R.id.connection_state);
        mDataFieldACTION_DATA_AVAILABLE = (TextView) findViewById(R.id.data_value_ACTION_DATA_AVAILABLE);
        mDataFieldEXTRA_DATA_BATTERY_STATUS = (TextView) findViewById(R.id.data_value_EXTRA_DATA_BATTERY_STATUS);
        mDataFieldEXTRA_DATA_SYSTOLIC_PROGRESS = (TextView) findViewById(R.id.data_value_EXTRA_DATA_SYSTOLIC_PROGRESS);
        mDataFieldEXTRA_DATA_BP_SYSTOLIC = (TextView) findViewById(R.id.data_value_EXTRA_DATA_BP_SYSTOLIC);
        mDataFieldEXTRA_DATA_BP_DIASTOLIC = (TextView) findViewById(R.id.data_value_EXTRA_DATA_BP_DIASTOLIC);
        mDataFieldEXTRA_DATA_BP_HEART_RATE = (TextView) findViewById(R.id.data_value_EXTRA_DATA_BP_HEART_RATE);
        mDataFieldEXTRA_DATA_ERROR = (TextView) findViewById(R.id.data_value_EXTRA_DATA_ERROR);
        /*
                ACTION_DATA_AVAILABLE,
                EXTRA_DATA_BATTERY_STATUS,
                EXTRA_DATA_SYSTOLIC_PROGRESS,
                EXTRA_DATA_BP_SYSTOLIC,
                EXTRA_DATA_BP_DIASTOLIC,
                EXTRA_DATA_BP_HEART_RATE,
                EXTRA_DATA_ERROR
                */

//        getActionBar().setTitle(mDeviceName);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gatt_services, menu);
        if (mConnected) {
            menu.findItem(R.id.menu_connect).setVisible(false);
            menu.findItem(R.id.menu_disconnect).setVisible(true);
        } else {
            menu.findItem(R.id.menu_connect).setVisible(true);
            menu.findItem(R.id.menu_disconnect).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_connect:
                mBluetoothLeService.connect(mDeviceAddress);
                return true;
            case R.id.menu_disconnect:
                mBluetoothLeService.disconnect();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateConnectionState(final int resourceId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mConnectionState.setText(resourceId);
            }
        });
    }

    private void displayData(String data, String field_data) {
        String packageName = getPackageName();
        Resources res = getResources() ;
        int resId = res.getIdentifier("data_value_" + field_data, "id", getPackageName());
        // getResources().getIdentifier("data_value_" + field_data, "id", getPackageName());
        TextView mDataFieldACTION_DATA_AVAILABLE = (TextView) findViewById(resId);
        if (data != null) {
            String a = mDataFieldACTION_DATA_AVAILABLE.getText().toString();
            mDataFieldACTION_DATA_AVAILABLE.setText(field_data + " :  "  + data);
        }
    }

    // Demonstrates how to iterate through the supported GATT Services/Characteristics.
    // In this sample, we populate the data structure that is bound to the ExpandableListView
    // on the UI.
    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        String uuid = null;
        String unknownServiceString = getResources().getString(R.string.unknown_service);
        String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData
                = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(
                    LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData =
                    new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics =
                    gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas =
                    new ArrayList<BluetoothGattCharacteristic>();
            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();

                currentCharaData.put(
                        LIST_NAME, SampleGattAttributes.lookup(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }

        SimpleExpandableListAdapter gattServiceAdapter = new SimpleExpandableListAdapter(
                this,
                gattServiceData,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {LIST_NAME, LIST_UUID},
                new int[] { android.R.id.text1, android.R.id.text2 },
                gattCharacteristicData,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {LIST_NAME, LIST_UUID},
                new int[] { android.R.id.text1, android.R.id.text2 }
        );
        mGattServicesList.setAdapter(gattServiceAdapter);
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }
}
