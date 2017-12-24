package com.cooey.devices.five_in_one.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class BTController {
    private static BTController mBtController = null;
    private final String TAG;
    private boolean isBTConnected;
    private BluetoothChatService mBluetoothChatService;
    private BluetoothAdapter mBtAdapter;
    private BroadcastReceiver mGattUpdateReceiver;
    Handler mHandler;
    public Listener mListener;

    class C09541 extends BroadcastReceiver {
        C09541() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.device.action.FOUND".equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                BTController.this.mListener.onFoundDevice(device);
                Log.i(BTController.this.TAG, "<<<Bluetooth Devices>>>  On Found Device : " + device.getName() + "  MAC:" + device.getAddress());
            } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                Log.i(BTController.this.TAG, "<<<Bluetooth Devices>>>  Stop Scan.......");
                BTController.this.mListener.onStopScan();
            } else if ("android.bluetooth.adapter.action.DISCOVERY_STARTED".equals(action)) {
                Log.i(BTController.this.TAG, "<<<Bluetooth Devices>>>  Start Scan.......");
                BTController.this.mListener.onStartScan();
            }
        }
    }

    class C09552 extends Handler {
        C09552() {
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 3:
                    switch (msg.arg1) {
                        case 0:
                            Log.i(BTController.this.TAG, "<<<Bluetooth Devices>>>  disconnected");
                            BTController.this.mListener.onDisconnected();
                            BTController.this.isBTConnected = false;
                            return;
                        case 2:
                            Log.i(BTController.this.TAG, "<<<Bluetooth Devices>>>  Connecting");
                            return;
                        case 3:
                            Log.i(BTController.this.TAG, "<<<Bluetooth Devices>>>  connected");
                            BTController.this.mListener.onConnected();
                            BTController.this.isBTConnected = true;
                            return;
                        default:
                            return;
                    }
                case 9:
                    BTController.this.mListener.onReceiveData((byte[]) msg.obj);
                    return;
                default:
                    return;
            }
        }
    }

    public interface Listener {
        void onConnected();

        void onDisconnected();

        void onFoundDevice(BluetoothDevice bluetoothDevice);

        void onReceiveData(byte[] bArr);

        void onStartScan();

        void onStopScan();
    }

    private BTController(Listener listener) {
        this.TAG = getClass().getName();
        this.mBtAdapter = null;
        this.isBTConnected = false;
        this.mGattUpdateReceiver = new C09541();
        this.mHandler = new C09552();
        this.mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mListener = listener;
    }

    public static BTController getDefaultBTController(Listener listener) {
        if (mBtController == null) {
            mBtController = new BTController(listener);
        }
        return mBtController;
    }

    public void enableBtAdpter() {
        if (!this.mBtAdapter.isEnabled()) {
            this.mBtAdapter.enable();
        }
    }

    public void disableBtAdpter() {
        if (this.mBtAdapter.isEnabled()) {
            this.mBtAdapter.disable();
        }
    }

    public boolean isBTConnected() {
        return this.isBTConnected;
    }

    public void startScan(boolean b) {
        if (b) {
            this.mBtAdapter.startDiscovery();
        } else {
            this.mBtAdapter.cancelDiscovery();
        }
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        return intentFilter;
    }

    public void connect(Context context, BluetoothDevice device) {
        this.mBluetoothChatService = new BluetoothChatService(context, this.mHandler);
        this.mBluetoothChatService.connect(device, true);
    }

    public void disconnect() {
        this.mBluetoothChatService.stop();
    }

    public void write(byte[] dat) {
        if (this.mBluetoothChatService != null) {
            this.mBluetoothChatService.write(dat);
        }
    }

    public void registerBroadcastReceiver(Context context) {
        context.registerReceiver(this.mGattUpdateReceiver, makeGattUpdateIntentFilter());
    }

    public void unregisterBroadcastReceiver(Context context) {
        context.unregisterReceiver(this.mGattUpdateReceiver);
    }
}
